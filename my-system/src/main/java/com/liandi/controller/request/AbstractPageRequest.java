package com.liandi.controller.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author czg
 * @date 2019/7/31 15:29
 * @description 分页请求参数基类
 */
@Data
public abstract class AbstractPageRequest {

    @NotNull(message = "当前页码不能为空")
    @Min(value = 1L, message = "当前页码不能小于1")
    private Integer currentPage;

    @NotNull(message = "每页大小不能为空")
    @Min(value = 1L, message = "每页大小不能小于1")
    private Integer pageSize;

}
