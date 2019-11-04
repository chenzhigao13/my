package com.liandi.system.job;

import java.io.Serializable;

import lombok.Data;

/**
 * job的pojo类
 * 
 * @author czg
 * @date 2019/10/28 14:31
 */
@Data
public class JobEntity implements Serializable {

    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 任务状态。正常
     */
    public static final String NORMAL = "01";

    /**
     * 任务状态。暂停
     */
    public static final String PAUSE = "02";

    private Long id;

    private String jobName;

    private String groupName;

    private String cron;

    /**
     * 任务状态。01：正常，02：暂停
     */
    private String status;

    private String beanName;

}
