package com.liandi.controller.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 登陆请求参数
 * 
 * @author czg
 * @date 2019/9/18 14:42
 */
@Data
public class LoginRequest {

    @NotBlank(message = "登陆名不能为空")
    private String loginName;

    @NotBlank(message = "密码不能为空")
    private String pswd;

}
