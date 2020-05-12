package com.liandi.system.redisson;

import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liandi.MySystemApplicationTests;

import lombok.extern.slf4j.Slf4j;

/**
 * Redisson 测试类
 * 
 * @author czg
 * @date 2019/12/9 14:50
 */
@Slf4j
public class RedissonTest extends MySystemApplicationTests {

    @Autowired
    private RedissonManager redissonManager;

    @Test
    public void testGetString() {
        String value = redissonManager.getString("getString");
        log.info("value: {}", value);
    }

    @Test
    public void testSetString() {
        redissonManager.setString("setString", "setString");
    }

    @Test
    public void testPutMapValue() {
        redissonManager.putMapValue("map", "a", "aa");
    }

    @Test
    public void testGetMapValue() {
        log.info("mapValue: {}", Optional.ofNullable(redissonManager.getMapValue("map", "a")));
    }

}
