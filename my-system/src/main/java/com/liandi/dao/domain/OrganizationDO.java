package com.liandi.dao.domain;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/7/25 10:21
 * @description 组织DO
 */
@Data
@TableName("sys_organization")
@Accessors(chain = true)
public class OrganizationDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("organization_name")
    private String organizationName;

    @TableField(value = "parent_organization_id", updateStrategy = FieldStrategy.IGNORED)
    private Long parentOrganizationId;

    private Integer sort;

    @TableField(exist = false)
    private OrganizationDO parentOrganization;

}
