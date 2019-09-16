package com.liandi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author czg
 * @date 2019/7/13 16:18:00
 * @description 启动类
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.liandi.dao")
@ServletComponentScan("com.liandi")
public class MySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySystemApplication.class, args);
    }

}
