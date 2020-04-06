package com.waqasabbasi.server;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.waqasabbasi.configuration.cassandra.CassandraConnection;
import com.waqasabbasi.configuration.zookeeper.ZKNode;
import com.waqasabbasi.configuration.zookeeper.ZooKeeperConnection;
import com.waqasabbasi.helloworld.*;
import com.waqasabbasi.repositories.URLRepository;
import com.waqasabbasi.types.CassandraException;
import com.waqasabbasi.types.ZookeeperException;
import com.waqasabbasi.utils.URLEncoder;
import com.waqasabbasi.configuration.redis.RedisConnection;

import io.grpc.stub.StreamObserver;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.io.IOException;

public class URLShortnerServiceImpl extends URLShortnerServiceGrpc.URLShortnerServiceImplBase {

    private int currentID;
    private int endIdRange;

    private Cluster cluster;

    private RedissonClient redissonClientMaster;
    private RedissonClient redissonClientSlave;

    private URLRepository urlRepository;

    final String ZOOKEEPER_HOST = System.getenv("ZOOKEEPER_HOST");
    final String CASSANDRA_HOST = System.getenv("CASSANDRA_SERVICE_HOST");
    final String REDIS_MASTER_HOST = System.getenv("REDIS_MASTER_HOST");
    final String REDIS_SLAVE_HOST = System.getenv("REDIS_SLAVE_HOST");

    private Logger logger;

    URLShortnerServiceImpl() {
        this.logger = Logger.getLogger(URLShortnerServiceImpl.class.getName());

        //Connect to Cassandra Cluster and Zookeeper. Both are necessary for this service
        try {
            //If either of these Environment variables are unknown then terminate service
            if (ZOOKEEPER_HOST == null) throw new ZookeeperException("Host Unknown");
            if (CASSANDRA_HOST == null) throw new CassandraException("Host Unknown");

            //Connect to Cassandra Cluster and create UrlRepository Instance based on the session
            this.cluster = CassandraConnection.getURLCluster(CASSANDRA_HOST);
            Session session = CassandraConnection.getSession(cluster);
            this.urlRepository = new URLRepository(session);

            //Connects to Zookeeper and gets ID Range for Unique ShortUrl Generation
            this.getIDRange(ZOOKEEPER_HOST);
        } catch (Exception e) {
            //If either Cassandra or Zookeeper are unable to connec to then terminate this service
            this.logger.log(Level.WARNING, "Unable to connect " + e.getMessage());
            this.logger.log(Level.WARNING, "Exiting Server Execution");
            this.terminate();
            System.exit(0);
        }

        //Connect to Redis Master-Slave. Service still functions using Cassandra even if Redis is unavailable
        //Master for writes.
        //Slave for reads.
        try {
            this.redissonClientMaster = RedisConnection.getConnection(REDIS_MASTER_HOST);
            this.redissonClientSlave = RedisConnection.getConnection(REDIS_SLAVE_HOST);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.FINE, "Unable to connect to Redis");
        }
    }

    //Terminate all active connections to Cassandra, Zookeeper or Redis
    public void terminate() {
        try {
            if (this.cluster != null && !this.cluster.isClosed()) {
                this.cluster.close();
            }

            if (this.redissonClientMaster != null && !this.redissonClientMaster.isShutdown()) {
                this.redissonClientMaster.shutdown();
            }

            if (this.redissonClientSlave != null && !this.redissonClientSlave.isShutdown()) {
                this.redissonClientSlave.shutdown();
            }

            this.logger.log(Level.INFO, "Terminated All Connections");
        } catch (Exception e) {
            System.out.println("Error Terminating");
            e.printStackTrace();
        }
    }

    URLShortnerServiceImpl(int startIdRange, int endIdRange) {
        this.currentID = startIdRange;
        this.endIdRange = endIdRange;
    }

    //gRPC Endppoint - Gets ShortUrl if it exists in cassandra otherwise it creates a new one
    @Override
    public void getShortURL(ShortURLRequest request, StreamObserver<ShortURLResponse> responseObserver) {

        String longUrl = request.getLongUrl();

        //Check Cassandra for longUrl
        String storedShortUrl = urlRepository.getShortUrl(longUrl);

        //URL Exists in Cassandra
        if (storedShortUrl.length() > 0) {
            this.logger.log(Level.INFO, "ShortUrl based on LongUrl found in Cassandra");
            ShortURLResponse response = ShortURLResponse.newBuilder().setShortUrl(storedShortUrl).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            return;
        }


        /*
        Since URL Doesnt not exist in cassandra, create a new one
         */
        if (currentID == endIdRange) {
            this.getIDRange(ZOOKEEPER_HOST);
        }

        int counter = currentID++;

        //Generate new shorturl based on counter value
        String shortUrl = URLEncoder.encode(counter);

        this.logger.log(Level.INFO, "New Url: " + longUrl + " => " + shortUrl);

        //Create client response and send with responseObserver
        ShortURLResponse response = ShortURLResponse.newBuilder().setShortUrl(shortUrl).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

        //Add to database
        urlRepository.addUrl(longUrl, shortUrl);

        //Add to Redis
        if (redissonClientMaster != null) {
            RBucket<String> bucketM = redissonClientMaster.getBucket(shortUrl);
            bucketM.set(longUrl);
        }

        //If all counter values / IDs have been used, ask ZooKeeper for new id range
        if (currentID == endIdRange) {
            this.getIDRange(ZOOKEEPER_HOST);
        }
    }

    //Get ID Range from Zookeeper
    public void getIDRange(String host) {
        ZooKeeper zooKeeper;
        try {
            zooKeeper = ZooKeeperConnection.connect(host);
            int[] result = ZKNode.getIdRange(zooKeeper);

            this.currentID = result[0];
            this.endIdRange = result[1];

            zooKeeper.close();
        } catch (IOException | InterruptedException | ZookeeperException | KeeperException e) {
            e.printStackTrace();
        }
    }

    //gRPC Endpoint - Get LongUrl given a shortUrl.
    //Checks Redis for long_url pair, if not present then checks cassandra, if not present returns empty string.
    @Override
    public void getLongUrl(LongUrlRequest request, StreamObserver<LongUrlResponse> responseObserver) {
        String shortUrl = request.getShortUrl();

        String bucketLongUrl = "";

        if (redissonClientSlave != null) {
            //Attempt to get longurl from redis memory
            RBucket<String> bucket = redissonClientSlave.getBucket(shortUrl);
            bucketLongUrl = bucket.get();
        }

        //If longurl exists in redis memory then return that.
        if (bucketLongUrl != null && bucketLongUrl.length() > 0) {
            this.logger.log(Level.INFO, "LongUrl based on ShortUrl found in Redis");
            LongUrlResponse response = LongUrlResponse.newBuilder().setLongUrl(bucketLongUrl).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();

            urlRepository.incrementCounter(shortUrl);
        } else {
             /*
            Check if url exists in Cassandra, if it does then retrieve it, return it and add to redis
            */
            String storedLongUrl = urlRepository.getLongUrl(shortUrl);
            LongUrlResponse.Builder response = LongUrlResponse.newBuilder();

            //If URL Exists in Cassandra, otherwise do not return anything
            if (storedLongUrl.length() > 0) {
                this.logger.log(Level.INFO, "LongUrl based on ShortUrl found in Cassandra");
                response.setLongUrl(storedLongUrl);
            }else{
                response.setLongUrl("");
            }

            responseObserver.onNext(response.build());
            responseObserver.onCompleted();

            //Save to Redis Master if the Url Existed in Cassandra Database but not in Redis
            if (redissonClientMaster != null && storedLongUrl.length() > 0) {
                RBucket<String> bucketM = redissonClientMaster.getBucket(shortUrl);
                bucketM.set(storedLongUrl);
            }
        }
    }
}
