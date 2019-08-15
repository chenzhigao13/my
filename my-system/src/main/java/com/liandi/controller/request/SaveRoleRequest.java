package com.liandi.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author czg
 * @date 2019/8/4 19:49
 * @description 保存角色请求参数
 */
@Data
public class SaveRoleRequest {

    @NotBlank(message = "角色名称不能为空")
    @Size(max = 128, message = "角色名称最大长度128")
    private String roleName;

}
