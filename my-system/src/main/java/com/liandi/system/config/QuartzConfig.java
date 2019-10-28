package com.liandi.system.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * quartz配置类
 * 
 * @author czg
 * @date 2019/10/19 15:27
 */
@Configuration
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {

        // quartz参数
        Properties prop = new Properties();

        // 调度器实例名称
        prop.put("org.quartz.scheduler.instanceName", "MyScheduler");
        // 实例ID
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        // 线程池
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        // 线程池里面的线程的数据，取值在1-100
        prop.put("org.quartz.threadPool.threadCount", "20");
        // 线程的优先级,取值在Thread.MIN_PRIORITY(1)到Thread.MAX_PRIORITY(10)
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // JobStore配置，JobStore是Scheduler在运行时用来存储相关的信息的，比如Job, Trigger。
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        // 是否集群，否
        prop.put("org.quartz.jobStore.isClustered", "true");
        // 分布式节点有效性检查时间间隔，单位：毫秒
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
        // JobStore 能处理的错过触发的 Trigger 的最大数量
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        // 触发器触发失败后再次触犯的时间间隔
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        // 表的前缀
        prop.put("org.quartz.jobStore.tablePrefix", "QUARTZ_");
        // 从 LOCKS 表查询一行并对这行记录加锁的 SQL 语句
        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");
        // PostgreSQL数据库，需要打开此注释
        // prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate");

        // 调度实例
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        // 设置调度属性
        schedulerFactoryBean.setQuartzProperties(prop);
        // 设置调度器实例名称
        schedulerFactoryBean.setSchedulerName("MyScheduler");
        // 延时启动，应用启动完后 QuartzScheduler 再启动
        schedulerFactoryBean.setStartupDelay(30);
        //
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContextKey");
        // QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        schedulerFactoryBean.setAutoStartup(true);
        // 设置数据源
        schedulerFactoryBean.setDataSource(dataSource);

        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(DataSource dataSource) {
        return schedulerFactoryBean(dataSource).getScheduler();
    }

}
