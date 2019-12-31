package com.liandi.system.redisson;

import org.redisson.api.RBucket;
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
        RBucket<String> bucket = redissonClient.getBucket(name);
        return bucket.get();
    }

    public void setString(String name, String value) {
        RBucket<String> bucket = redissonClient.getBucket(name);
        bucket.set(value);
    }

    public void putMapValue(String name, String key, Object value) {
        redissonClient.getMap(name).put(key, value);
    }

    public Object getMapValue(String name, String key) {
        return redissonClient.getMap(name).get(key);
    }

}
