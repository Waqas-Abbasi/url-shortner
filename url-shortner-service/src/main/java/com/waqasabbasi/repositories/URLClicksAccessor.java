package com.waqasabbasi.repositories;

import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

/**
 * URLClicksAccessor contains methods to query url_clicks table in Cassandra
 */
@Accessor
public interface URLClicksAccessor {

    /**
     * Increments the UrlClick counter of a shortUrl
     * @param shortUrl - The shortUrl to Increment
     */
    @Query("UPDATE url_info.url_clicks SET clicks = clicks + 1 WHERE short_url=?")
    void incrementClick(String shortUrl);
}
