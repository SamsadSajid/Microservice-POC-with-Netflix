package com.microservice.poc.auth.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String hash, Object key, Object value) {
        redisTemplate.opsForHash()
                .put(hash, key, value);
    }

    public void setTTL(String hash, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(hash, timeout, timeUnit);
    }
}
