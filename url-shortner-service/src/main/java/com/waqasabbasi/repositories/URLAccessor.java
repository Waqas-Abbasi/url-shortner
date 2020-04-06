package com.waqasabbasi.repositories;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.waqasabbasi.models.LongURL;
import com.waqasabbasi.models.ShortURL;

/**
 * URLAccessor contains methods to query in short_url & long_url Tables in Cassandra
 */
@Accessor
interface URLAccessor {

    /**
     * Search ShortUrl given longUrl
     * @param longUrl - the longUrl to search the shortUrl by
     * @return all shortUrl(s) with input longUrl
     */
    @Query("SELECT * FROM url_info.long_url WHERE long_url=?")
    Result<LongURL> getShortUrl(String longUrl);

    /**
     * Search longUrl given shortUrl
     * @param shortUrl - the shortUrl to search the longUrl by
     * @return all longUrl(s) with input shortUrl
     */
    @Query("SELECT * FROM url_info.short_url WHERE short_url=?")
    Result<ShortURL> getLongUrl(String shortUrl);
}