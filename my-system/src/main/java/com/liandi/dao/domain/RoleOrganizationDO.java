package com.liandi.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/15 16:02
 * @description 角色与组织关系DO
 */
@Data
@TableName("sys_role_organization")
@Accessors(chain = true)
public class RoleOrganizationDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("organization_id")
    private Long organizationId;

}
