package com.liandi.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/7/25 10:53
 * @description 用户与角色关联DO
 */
@Data
@TableName("sys_user_role")
@Accessors(chain = true)
public class UserRoleDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;

}
