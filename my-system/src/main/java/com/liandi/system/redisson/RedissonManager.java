package com.liandi.system.redisson;

import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Redisson管理
 * 
 * @author czg
 * @date 2019/12/10 15:55
 */
@Component
public class RedissonManager {

    @Autowired
    private RedissonClient redissonClient;

    public String getString(String name) {
        RBucket<String> rBucket = redissonClient.getBucket(name);
        return rBucket.get();
    }

    public void setString(String name, String value) {
        RBucket<String> rBucket = redissonClient.getBucket(name);
        rBucket.set(value);
    }

    public <K, V> V putMapValue(String name, K key, V value) {
        RMap<K, V> rMap = redissonClient.getMap(name);
        return rMap.put(key, value);
    }

    public <K, V> V getMapValue(String name, K key) {
        RMap<K, V> rMap = redissonClient.getMap(name);
        return rMap.get(key);
    }

    public <K, V> V delMapKey(String name, K key) {
        RMap<K, V> rMap = redissonClient.getMap(name);
        return rMap.remove(key);
    }

}
