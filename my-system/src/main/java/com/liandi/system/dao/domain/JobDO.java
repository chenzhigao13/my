package com.liandi.system.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * job的DO
 * 
 * @author czg
 * @date 2019/10/24 14:57
 */
@Data
@TableName("scheduler_job")
public class JobDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("job_name")
    private String jobName;

    @TableField("group_name")
    private String groupName;

    @TableField("cron")
    private String cron;

    @TableField("description")
    private String description;

    /**
     * 任务状态。01：正常，02：暂停
     */
    @TableField("status")
    private String status;

    @TableField("bean_name")
    private String beanName;

}
