package com.liandi.util;

import com.liandi.controller.request.AbstractPageRequest;
import com.liandi.dao.param.AbstractPageParam;

/**
 * @author czg
 * @date 2019/8/2 14:48
 * @description 分页计算工具类
 */
public class PageUtil {

    private PageUtil() {}

    public static void setSize(AbstractPageParam pageParam, AbstractPageRequest pageRequest) {
        pageParam.setStartSize((pageRequest.getCurrentPage() - 1) * pageRequest.getPageSize());
        pageParam.setPageSize(pageRequest.getPageSize());
    }

}
