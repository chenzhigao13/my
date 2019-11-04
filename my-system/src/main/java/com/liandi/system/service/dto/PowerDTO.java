package com.liandi.system.service.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/4 17:39
 * @description 权限DTO
 */
@Data
@Accessors(chain = true)
public class PowerDTO {

    private Long id;

    private String powerName;

    private String powerUrl;

    private String powerType;

    private String icon;

    private Long parentPowerId;

    private Integer sort;

    private List<PowerDTO> childPowerList;

}
