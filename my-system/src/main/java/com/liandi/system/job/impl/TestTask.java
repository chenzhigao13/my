package com.liandi.system.job.impl;

import org.springframework.stereotype.Component;

import com.liandi.system.job.ITask;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试定时任务
 * 
 * @author czg
 * @date 2019/10/25 15:25
 */
@Component("testTask")
@Slf4j
public class TestTask implements ITask {

    @Override
    public void run() {

        log.debug("测试任务执行了");

    }

}
