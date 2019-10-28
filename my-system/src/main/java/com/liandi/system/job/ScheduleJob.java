package com.liandi.system.job;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.liandi.system.util.SpringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 执行定时任务
 * 
 * @author czg
 * @date 2019/10/28 15:50
 */
@Slf4j
public class ScheduleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) {

        JobEntity jobEntity = (JobEntity)context.getMergedJobDataMap().get(JobEntity.JOB_PARAM_KEY);

        // 任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            // 执行任务
            log.debug("任务准备执行，任务ID：{}", jobEntity.getId());

            ITask target = SpringUtil.getBean(jobEntity.getBeanName(), ITask.class);
            target.run();

            // 任务执行总时长
            long times = System.currentTimeMillis() - startTime;

            log.debug("任务执行完毕，任务ID：{}，总共耗时：{}毫秒", jobEntity.getId(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：{}", jobEntity.getId(), e);
        }

    }

}
