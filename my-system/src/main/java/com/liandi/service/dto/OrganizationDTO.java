package com.liandi.service.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/9 15:50
 * @description 组织DTO
 */
@Data
@Accessors(chain = true)
public class OrganizationDTO {

    private Long id;

    private String organizationName;

    private Long parentOrganizationId;

    private Integer sort;

    private String parentOrganizationName;

    private List<OrganizationDTO> childOrganizationList;

}
