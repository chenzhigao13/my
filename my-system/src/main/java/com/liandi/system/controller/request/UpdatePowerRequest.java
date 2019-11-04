package com.liandi.system.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author czg
 * @date 2019/8/4 16:59
 * @description 更新权限请求参数
 */
@Data
public class UpdatePowerRequest {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotBlank(message = "权限名称不能为空")
    @Size(max = 128, message = "权限名称最长128位")
    private String powerName;

    @Size(max = 128, message = "权限URL最长128位")
    private String powerUrl;

    @NotBlank(message = "权限类型不能为空")
    private String powerType;

    @Size(max = 32, message = "图标最长32位")
    private String icon;

    private Long parentPowerId;

    private Integer sort;

}
