package com.liandi.system.dao.param;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询任务参数
 * 
 * @author czg
 * @date 2019/10/29 17:14
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListPageJobParam extends AbstractPageParam {

    private String jobName;

    private String groupName;

    private String status;

    private String beanName;

}
