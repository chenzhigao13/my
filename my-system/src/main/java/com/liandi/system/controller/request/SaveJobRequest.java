package com.liandi.system.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 保存Job的请求参数
 * 
 * @author czg
 * @date 2019/10/28 14:59
 */
@Data
public class SaveJobRequest {

    @NotBlank(message = "任务名称不能为空")
    @Size(max = 42, message = "任务名称最长42位")
    private String jobName;

    @NotBlank(message = "任务名称不能为空")
    @Size(max = 42, message = "任务名称最长42位")
    private String groupName;

    @NotBlank(message = "cron表达式不能为空")
    @Size(max = 32, message = "cron表达式最长32位")
    private String cron;

    @Size(max = 170, message = "描述最长170位")
    private String description;

    /**
     * 任务状态。01：正常，02：暂停
     */
    @NotBlank(message = "任务状态不能为空")
    // @DesignatedValue(value = {JobStatusEnum.NORMAL})
    private String status;

    @NotBlank(message = "bean名称不能为空")
    @Size(max = 32, message = "bean名称最长32位")
    private String beanName;

}
