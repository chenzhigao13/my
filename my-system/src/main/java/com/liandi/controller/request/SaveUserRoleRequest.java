package com.liandi.controller.request;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author czg
 * @date 2019/8/16 9:21
 * @description 保存用户角色关系请求参数
 */
@Data
public class SaveUserRoleRequest {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotEmpty(message = "角色ID不能为空")
    private Set<Long> roleIdSet;

}
