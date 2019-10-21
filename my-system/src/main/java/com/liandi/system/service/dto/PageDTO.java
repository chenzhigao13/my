package com.liandi.system.service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author czg
 * @date 2019/7/31 15:44
 * @description 分页DTO
 */
@Data
@AllArgsConstructor
public class PageDTO<T> {

    private Integer count;

    private List<T> pageData;

}
