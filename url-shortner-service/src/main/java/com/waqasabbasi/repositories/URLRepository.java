package com.waqasabbasi.repositories;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.waqasabbasi.models.LongURL;
import com.waqasabbasi.models.ShortURL;

import java.util.List;

/**
 * Repository of Methods to handle Cassandra Database requests related to Urls
 */
public class URLRepository {

    //Static Mappers and Accessors to prepare statements (To improve performance)
    private static Mapper<LongURL> longURLMapper;
    private static Mapper<ShortURL> shortURLMapper;
    private static URLClicksAccessor urlClickAccessor;
    private static URLAccessor urlAccessor;

    public URLRepository(Session session) {

        //Initialise all Mappers
        //Initialise all Accessors to prepare query statements
        MappingManager manager = new MappingManager(session);
        longURLMapper = manager.mapper(LongURL.class);
        shortURLMapper = manager.mapper(ShortURL.class);
        urlClickAccessor = manager.createAccessor(URLClicksAccessor.class);
        urlAccessor = manager.createAccessor(URLAccessor.class);
    }


    /**
     * Add shortUrl and longUrl pair to short_url table and long_url table
     */
    public void addUrl(String longUrl, String shortUrl) {
        shortURLMapper.save(new ShortURL(longUrl, shortUrl));
        longURLMapper.save(new LongURL(longUrl, shortUrl));
        urlClickAccessor.incrementClick(shortUrl);
    }


    /**
     * Get shortUrl given longUrl (param)
     *
     * @return shortUrl if longUrl exists in long_url table otherwise return ""
     */
    public String getShortUrl(String longUrl) {
        //Search long_url table for long_url and short_url pair
        Result<LongURL> url = urlAccessor.getShortUrl(longUrl);
        List<LongURL> results = url.all();

        //If found return the first result (Only 1 short_url and long_url pair can exist)
        if (results.size() > 0) {
            LongURL result = results.get(0);
            String shortUrl = result.getShort_url();

            if (shortUrl != null) return shortUrl;
        }

        return "";
    }


    /**
     * Get longUrl given shortUrl (param)
     *
     * @return longUrl if shortUrl exists in short_url table otherwise return ""
     */
    public String getLongUrl(String shortUrl) {
        //Search short_url table for short_url and long_url pair
        Result<ShortURL> url = urlAccessor.getLongUrl(shortUrl);

        List<ShortURL> results = url.all();

        //If found return the first result (Only 1 short_url and long_url pair can exist)
        if (results.size() > 0) {
            ShortURL result = results.get(0);
            String longUrl = result.getLong_url();

            if (longUrl != null) return longUrl;
        }

        return "";
    }

    //Increment Click Counter in url_clicks counter based on short_url
    public void incrementCounter(String shortUrl) {
        urlClickAccessor.incrementClick(shortUrl);
    }
}
