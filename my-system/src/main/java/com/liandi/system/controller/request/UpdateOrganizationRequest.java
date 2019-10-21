package com.liandi.system.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author czg
 * @date 2019/8/6 18:56
 * @description 修改组织请求参数
 */
@Data
public class UpdateOrganizationRequest {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotBlank(message = "组织名称不能为空")
    @Size(max = 128, message = "组织名称最长128位")
    private String organizationName;

    private Long parentOrganizationId;

    private Integer sort;

}
