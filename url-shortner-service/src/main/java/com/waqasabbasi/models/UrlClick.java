package com.waqasabbasi.models;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;


/**
 * Method Maps URLClick class to Cassandra Database Table (url_info.url_clicks)
 */
@Table(keyspace = "url_info", name = "url_clicks")
public class UrlClick {

    @PartitionKey()
    private String short_url;

    @Column(name = "clicks")
    private int urlClicks;

    public UrlClick() { }

    public UrlClick(String shortUrl, int urlClicks) {
        this.short_url = shortUrl;
        this.urlClicks = urlClicks;
    }
}
