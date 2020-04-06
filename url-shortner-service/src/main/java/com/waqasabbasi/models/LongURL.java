package com.waqasabbasi.models;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/**
 * Method Maps LongURL Class to Cassandra Database Table (url_info.long_url)
 */
@Table(keyspace = "url_info", name = "long_url")
public class LongURL extends URL {

    LongURL(){ }

    public LongURL(String long_url, String short_url) {
        super(long_url, short_url);
    }

    @PartitionKey()
    private String long_url;

    private String short_url;
}