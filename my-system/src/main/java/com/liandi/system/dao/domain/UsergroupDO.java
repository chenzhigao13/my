package com.liandi.system.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * @author czg
 * @date 2019/7/25 13:48
 * @description 用户组DO
 */
@Data
@TableName("sys_usergroup")
public class UsergroupDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("usergroup_name")
    private String usergroupName;

}
