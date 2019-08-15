package com.liandi.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author czg
 * @date 2019/7/31 15:32
 * @description 用户分页请求参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryUserRequest extends AbstractPageRequest {

    private String userCode;

    private String userName;

    private Long organizationId;

    private String phone;

    private String email;
}
