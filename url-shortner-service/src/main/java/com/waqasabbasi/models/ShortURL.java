package com.waqasabbasi.models;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

/**
 * Method Maps ShortURL Class to Cassandra Database Table (url_info.short_url)
 */
@Table(keyspace = "url_info", name = "short_url")
public class ShortURL extends URL {

    ShortURL(){}

    public ShortURL(String long_url, String short_url) {
        super(long_url, short_url);
    }

    @PartitionKey()
    private String short_url;

    private String long_url;
}