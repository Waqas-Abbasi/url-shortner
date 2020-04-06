package com.waqasabbasi.configuration.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.waqasabbasi.types.CassandraException;

/**
 * Helper Methods to get different connections in Cassandra
 */
public class CassandraConnection {

    /**
     * @return Cluster Connection
     */
    public static Cluster getURLCluster(String host) {

        return Cluster.builder()
                .addContactPoint(host)
                .build();
    }


    /**
     * @param cluster -> Cluster Connection to create a session
     * @return Cluster Session -> Returns a new Cluster Session (Connection)
     * @throws CassandraException -> If unable to connect to cluster or unable to create a session
     */
    public static Session getSession(Cluster cluster) throws CassandraException {
        Session session;
        try {
            cluster.newSession();
            session = cluster.connect();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CassandraException("Failed to create cassandra session");
        }

        return session;
    }

}
