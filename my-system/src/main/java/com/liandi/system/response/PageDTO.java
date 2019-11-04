package com.liandi.system.response;

import java.util.List;

import lombok.Data;

/**
 * 分页DTO
 * 
 * @author czg
 * @date 2019/7/31 15:44
 */
@Data
public final class PageDTO<T> {

    private Integer count;

    private List<T> pageData;

    private PageDTO(Integer count, List<T> pageData) {
        this.count = count;
        this.pageData = pageData;
    }

    public static <T> PageDTO<T> of(Integer count, List<T> pageData) {
        return new PageDTO<>(count, pageData);
    }

}
