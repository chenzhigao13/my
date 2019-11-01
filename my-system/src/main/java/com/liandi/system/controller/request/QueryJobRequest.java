package com.liandi.system.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询任务请求参数
 * 
 * @author czg
 * @date 2019/10/29 16:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryJobRequest extends AbstractPageRequest {

    private String jobName;

    private String groupName;

    /**
     * 任务状态。01：正常，02：暂停
     */
    private String status;

    private String beanName;

}
