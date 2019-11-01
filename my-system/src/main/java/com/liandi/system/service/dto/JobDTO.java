package com.liandi.system.service.dto;

import lombok.Data;

/**
 * 任务DTO类
 * 
 * @author czg
 * @date 2019/10/29 16:40
 */
@Data
public class JobDTO {

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
