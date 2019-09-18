package com.liandi.dao.domain;

import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/7/19 10:58
 * @description 角色DO类
 */
@Data
@TableName("sys_role")
@Accessors(chain = true)
public class RoleDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("role_name")
    private String roleName;

    @TableField("role_code")
    private String roleCode;

    @TableField(exist = false)
    private List<PowerDO> powerList;

}
