package com.cloud.frame.demo.auth.manager;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by wd on 2018/4/4.
 */
public class ShiroRedisCacheManager implements CacheManager {

    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String arg0) throws CacheException {
        return new ShiroRedisCache<>(arg0, redisTemplate);
    }
}
