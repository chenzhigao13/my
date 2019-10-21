package com.liandi.system.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author czg
 * @date 2019/7/25 10:21
 * @description 保存组织请求参数
 */
@Data
public class SaveOrganizationRequest {

    @NotBlank(message = "组织名称不能为空")
    @Size(max = 128, message = "组织名称最长128位")
    private String organizationName;

    private Long parentOrganizationId;

    private Integer sort;

}
