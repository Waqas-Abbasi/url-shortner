package com.waqasabbasi.configuration.zookeeper;

import com.waqasabbasi.types.ZookeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZooKeeperConnection {
    // Method to connect zookeeper ensemble.
    public static ZooKeeper connect(String host) throws IOException, InterruptedException, ZookeeperException {

        final CountDownLatch connectedSignal = new CountDownLatch(1);

        ZooKeeper zooKeeper = new ZooKeeper(host, 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });

        //Keep retrying to connect to Zookeeper for 5 seconds
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            connectedSignal.countDown();
        }).start();

        connectedSignal.await();

        if (zooKeeper.getState() == ZooKeeper.States.CLOSED) {
            throw new ZookeeperException("Host Unknown");
        }

        return zooKeeper;
    }


    public static boolean isClosed(ZooKeeper zooKeeper) {
        return zooKeeper.getState() == ZooKeeper.States.CLOSED;
    }
}

