package com.liandi.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author czg
 * @date 2019/7/16 9:34
 * @description 保存用户请求参数
 */
@Data
public class SaveUserRequest {

    @NotBlank(message = "用户编号不能为空")
    @Size(max = 32, message = "用户编号最长32位")
    private String userCode;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 32, message = "用户名最长32位")
    private String userName;

    @NotBlank(message = "账号不能为空")
    @Size(max = 32, message = "账号最长32位")
    private String loginName;

    @NotNull(message = "所属组织不能为空")
    private Long organizationId;

    @Size(max = 32, message = "电话最长32位")
    private String phone;

    @Size(max = 128, message = "邮箱最长128位")
    private String email;

}
