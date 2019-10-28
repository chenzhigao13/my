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

    private Long id;

    private String jobName;

    private String groupName;

    private String cron;

    private String description;

    /**
     * 任务状态。01：正常，02：暂停
     */
    private String status;

    private String beanName;

}
