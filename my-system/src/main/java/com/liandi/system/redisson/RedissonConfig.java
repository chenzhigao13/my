package com.liandi.system.redisson;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * Redisson的配置类
 * 
 * @author czg
 * @date 2019/12/9 14:26
 */
@Slf4j
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {
        return Redisson.create(Config.fromYAML(RedissonConfig.class.getResourceAsStream("/redisson-config.yaml")));
    }

}
