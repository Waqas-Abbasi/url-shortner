package com.waqasabbasi.configuration.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.redisson.config.Config;

public class RedisConnection {


    /**
     * @param host -> Redis Host IP
     * @return RedissonClient -> Returns a Redis Client Connection
     */
    public static RedissonClient getConnection(String host) {
        RedissonClient redisClient;

        try {
            Config config = new Config();
            config.useSingleServer().setAddress("redis://" + host + ":6379");
            redisClient = Redisson.create(config);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RedisException("Unable to create Redisson Client");
        }

        return redisClient;
    }

}
