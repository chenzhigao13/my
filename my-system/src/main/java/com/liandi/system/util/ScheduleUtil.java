package com.liandi.system.util;

import org.apache.commons.lang3.StringUtils;
import org.quartz.*;

import com.liandi.system.constant.JobStatusEnum;
import com.liandi.system.exception.SystemException;
import com.liandi.system.job.JobEntity;
import com.liandi.system.job.ScheduleJob;
import com.liandi.system.response.ResponseEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * 定时任务工具类
 * 
 * @author czg
 * @date 2019/10/28 14:35
 */
@Slf4j
public class ScheduleUtil {

    private final static String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     */
    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger)scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("获取定时任务CronTrigger异常", e);
            throw new SystemException(ResponseEnum.GET_CRON_TRIGGER_ERROR);
        }
    }

    public static void createScheduleJob(Scheduler scheduler, JobEntity jobEntity) {
        try {
            // 构建job信息
            JobDetail jobDetail =
                JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(jobEntity.getId())).build();

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule(jobEntity.getCron()).withMisfireHandlingInstructionDoNothing();

            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(jobEntity.getId()))
                .withSchedule(scheduleBuilder).build();

            // 放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(JobEntity.JOB_PARAM_KEY, jobEntity);

            scheduler.scheduleJob(jobDetail, trigger);

            // 暂停任务
            if (StringUtils.equals(JobStatusEnum.PAUSE.getStatus(), jobEntity.getStatus())) {
                pauseJob(scheduler, jobEntity.getId());
            }
        } catch (SchedulerException e) {
            log.error("创建定时任务异常：", e);
            throw new SystemException(ResponseEnum.CREATE_SCHEDULE_JOB_ERROR);
        }
    }

    public static void updateScheduleJob(Scheduler scheduler, JobEntity jobEntity) {
        try {
            TriggerKey triggerKey = getTriggerKey(jobEntity.getId());

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule(jobEntity.getCron()).withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, jobEntity.getId());

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 参数
            trigger.getJobDataMap().put(JobEntity.JOB_PARAM_KEY, jobEntity);

            scheduler.rescheduleJob(triggerKey, trigger);

            // 暂停任务
            if (StringUtils.equals(JobStatusEnum.PAUSE.getStatus(), jobEntity.getStatus())) {
                pauseJob(scheduler, jobEntity.getId());
            }

        } catch (SchedulerException e) {
            log.error("更新定时任务异常：", e);
            throw new SystemException(ResponseEnum.UPDATE_SCHEDULE_JOB_ERROR);
        }
    }

    public static void run(Scheduler scheduler, JobEntity jobEntity) {
        try {
            // 参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(JobEntity.JOB_PARAM_KEY, jobEntity);

            scheduler.triggerJob(getJobKey(jobEntity.getId()), dataMap);
        } catch (SchedulerException e) {
            log.error("立即执行定时任务异常：", e);
            throw new SystemException(ResponseEnum.RUN_SCHEDULE_JOB_AT_ONCE_ERROR);
        }
    }

    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("暂停定时任务异常：", e);
            throw new SystemException(ResponseEnum.PAUSE_SCHEDULE_JOB_ERROR);
        }
    }

    public static void resumeJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("恢复定时任务异常：", e);
            throw new SystemException(ResponseEnum.RESUME_SCHEDULE_JOB_ERROR);
        }
    }

    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("删除定时任务异常：", e);
            throw new SystemException(ResponseEnum.DELETE_SCHEDULE_JOB_ERROR);
        }
    }
}
