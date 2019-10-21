package com.liandi.system.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.liandi.system.controller.request.SaveRolePowerOrganizationRequest;
import com.liandi.system.controller.request.SaveRoleRequest;
import com.liandi.system.controller.request.UpdateRoleRequest;
import com.liandi.system.dao.*;
import com.liandi.system.dao.domain.*;
import com.liandi.system.exception.SystemException;
import com.liandi.system.response.ResponseEnum;
import com.liandi.system.service.RoleService;

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

    @Autowired
    private RoleOrganizationMapper roleOrganizationMapper;

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

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveRolePowerAndOrganization(SaveRolePowerOrganizationRequest saveRolePowerOrganizationRequest) {

        Long roleId = saveRolePowerOrganizationRequest.getRoleId();
        if (Objects.isNull(roleMapper.selectById(roleId))) {
            throw new SystemException("角色不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        rolePowerMapper.delete(new QueryWrapper<>(new RolePowerDO().setRoleId(roleId)));

        roleOrganizationMapper.delete(new QueryWrapper<>(new RoleOrganizationDO().setRoleId(roleId)));

        Set<Long> powerIdSet = saveRolePowerOrganizationRequest.getPowerIdSet();
        List<RolePowerDO> rolePowerList = Lists.newArrayListWithCapacity(powerIdSet.size());
        RolePowerDO rolePower;
        for (Long powerId : powerIdSet) {
            rolePower = new RolePowerDO().setId(IdWorker.getId()).setPowerId(powerId).setRoleId(roleId);
            rolePowerList.add(rolePower);
        }
        rolePowerMapper.batchSaveRolePower(rolePowerList);

        Set<Long> organizationIdSet = saveRolePowerOrganizationRequest.getOrganizationIdSet();
        List<RoleOrganizationDO> roleOrganizationList = Lists.newArrayListWithCapacity(organizationIdSet.size());
        RoleOrganizationDO roleOrganization;
        for (Long organizationId : organizationIdSet) {
            roleOrganization =
                new RoleOrganizationDO().setId(IdWorker.getId()).setRoleId(roleId).setOrganizationId(organizationId);
            roleOrganizationList.add(roleOrganization);
        }
        roleOrganizationMapper.batchSaveRoleOrganization(roleOrganizationList);

    }

}
