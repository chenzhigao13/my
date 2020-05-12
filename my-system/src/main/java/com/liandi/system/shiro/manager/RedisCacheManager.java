package com.liandi.system.shiro.manager;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * redis缓存管理器
 * 
 * @author czg
 * @date 2019/12/16 15:36
 */
public class RedisCacheManager extends AbstractCacheManager {

    @Autowired
    private Cache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }

    @Override
    protected Cache createCache(String s) throws CacheException {
        return redisCache;
    }

    @Override
    public void destroy() {

    }

}
