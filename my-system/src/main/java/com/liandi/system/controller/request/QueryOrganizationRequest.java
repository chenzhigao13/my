package com.liandi.system.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author czg
 * @date 2019/8/13 20:12
 * @description 分页查询组织请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryOrganizationRequest extends AbstractPageRequest {

    private String organizationName;

    private Long parentOrganizationId;

}
