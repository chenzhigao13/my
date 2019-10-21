package com.liandi.system.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/7/25 13:58
 * @description 用户组与用户关联DO
 */
@Data
@TableName("sys_usergroup_user")
@Accessors(chain = true)
public class UsergroupUserDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("usergroup_id")
    private Long usergroupId;

    @TableField("user_id")
    private Long userId;

}
