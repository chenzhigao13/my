package com.liandi.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liandi.controller.request.SaveRoleRequest;
import com.liandi.controller.request.UpdateRoleRequest;
import com.liandi.dao.RoleMapper;
import com.liandi.dao.RolePowerMapper;
import com.liandi.dao.UserRoleMapper;
import com.liandi.dao.UsergroupRoleMapper;
import com.liandi.dao.domain.RoleDO;
import com.liandi.dao.domain.RolePowerDO;
import com.liandi.dao.domain.UserRoleDO;
import com.liandi.dao.domain.UsergroupRoleDO;
import com.liandi.exception.SystemException;
import com.liandi.response.ResponseEnum;
import com.liandi.service.RoleService;

/**
 * @author czg
 * @date 2019/7/19 11:26
 * @description 角色Service接口实现
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePowerMapper rolePowerMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UsergroupRoleMapper usergroupRoleMapper;

    @Override
    public void saveRole(SaveRoleRequest saveRoleRequest) {
        roleMapper.insert(new RoleDO().setRoleName(saveRoleRequest.getRoleName()));
    }

    @Override
    public void updateRole(UpdateRoleRequest updateRoleRequest) {

        RoleDO role = roleMapper.selectById(updateRoleRequest.getId());
        if (Objects.isNull(role)) {
            throw new SystemException("角色不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        RoleDO updateRoleParam = new RoleDO();
        updateRoleParam.setId(updateRoleRequest.getId());
        updateRoleParam.setRoleName(updateRoleRequest.getRoleName());
        roleMapper.updateById(updateRoleParam);

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteRole(Long id) {

        if (Objects.isNull(roleMapper.selectById(id))) {
            throw new SystemException("角色不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        roleMapper.deleteById(id);

        rolePowerMapper.delete(new QueryWrapper<>(new RolePowerDO().setRoleId(id)));

        userRoleMapper.delete(new QueryWrapper<>(new UserRoleDO().setRoleId(id)));

        usergroupRoleMapper.delete(new QueryWrapper<>(new UsergroupRoleDO().setRoleId(id)));

    }

}
