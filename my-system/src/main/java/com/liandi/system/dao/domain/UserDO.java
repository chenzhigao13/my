package com.liandi.system.dao.domain;

import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/7/19 9:07
 * @description 用户DO
 */
@Data
@TableName("sys_user")
@Accessors(chain = true)
public class UserDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("user_code")
    private String userCode;

    @TableField("user_name")
    private String userName;

    @TableField("login_name")
    private String loginName;

    private String pswd;

    @TableField("organization_id")
    private Long organizationId;

    private String phone;

    private String email;

    @TableField(exist = false)
    private List<RoleDO> roleList;

    @TableField(exist = false)
    private OrganizationDO organization;

    @TableField(exist = false)
    private Set<String> roleCodeSet;

    @TableField(exist = false)
    private Set<String> powerUrlSet;

}
