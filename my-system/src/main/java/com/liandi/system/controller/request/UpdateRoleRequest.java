package com.liandi.system.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author czg
 * @date 2019/8/4 19:55
 * @description 更新角色请求参数
 */
@Data
public class UpdateRoleRequest {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotBlank(message = "角色名称不能为空")
    @Size(max = 128, message = "角色名称最大长度128")
    private String roleName;

}
