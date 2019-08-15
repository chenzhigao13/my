package com.liandi.dao.param;

import lombok.Data;

/**
 * @author czg
 * @date 2019/8/2 14:20
 * @description 分页查询参数基类
 */
@Data
public abstract class AbstractPageParam {

    private Integer startSize;

    private Integer pageSize;

}
