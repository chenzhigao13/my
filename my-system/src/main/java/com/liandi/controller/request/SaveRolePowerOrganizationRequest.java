package com.liandi.controller.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author czg
 * @date 2019/8/15 20:03
 * @description 保存角色权限和组织请求参数
 */
@Data
public class SaveRolePowerOrganizationRequest {

    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @NotEmpty(message = "权限ID不能为空")
    private Set<Long> powerIdSet;

    @NotEmpty(message = "组织ID不能为空")
    private Set<Long> organizationIdSet;

}
