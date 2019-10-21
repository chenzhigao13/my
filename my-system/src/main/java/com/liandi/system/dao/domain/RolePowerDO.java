package com.liandi.system.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/7/25 10:38
 * @description 角色与权限关联DO
 */
@Data
@TableName("sys_role_power")
@Accessors(chain = true)
public class RolePowerDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("power_id")
    private Long powerId;

}
