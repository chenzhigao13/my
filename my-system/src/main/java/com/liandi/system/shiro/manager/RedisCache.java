package com.liandi.system.shiro.manager;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.liandi.system.redisson.RedissonManager;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author czg
 * @date 2019/12/16 15:40
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    /**
     * 缓存前缀
     */
    private static final String CACHE_PREFIX = "shiro-cache:";

    @Autowired
    private RedissonManager redissonManager;

    @Override
    public V get(K k) throws CacheException {
        if (Objects.isNull(k)) {
            return null;
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    private byte[] getByteKey(K key) {
        if (key instanceof String) {
            return (CACHE_PREFIX + key).getBytes();
        }
        return ObjectUtil.serialize(key);
    }

}
