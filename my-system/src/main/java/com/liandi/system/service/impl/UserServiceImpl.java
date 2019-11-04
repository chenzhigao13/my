package com.liandi.system.service.impl;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.Lists;
import com.liandi.system.controller.request.*;
import com.liandi.system.dao.UserMapper;
import com.liandi.system.dao.UserRoleMapper;
import com.liandi.system.dao.UsergroupUserMapper;
import com.liandi.system.dao.domain.OrganizationDO;
import com.liandi.system.dao.domain.UserDO;
import com.liandi.system.dao.domain.UserRoleDO;
import com.liandi.system.dao.domain.UsergroupUserDO;
import com.liandi.system.dao.param.ListPageUserParam;
import com.liandi.system.exception.SystemException;
import com.liandi.system.response.PageDTO;
import com.liandi.system.response.ResponseEnum;
import com.liandi.system.service.UserService;
import com.liandi.system.service.dto.UserDTO;
import com.liandi.system.util.PageUtil;

/**
 * @author czg
 * @date 2019/7/16 9:58
 * @description 用户Service接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${user.init-pswd}")
    private String initPswd;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UsergroupUserMapper usergroupUserMapper;

    @Override
    public PageDTO<UserDTO> queryUser(QueryUserRequest queryUserRequest) {

        ListPageUserParam listPageUserParam = new ListPageUserParam();
        listPageUserParam.setEmail(queryUserRequest.getEmail()).setOrganizationId(queryUserRequest.getOrganizationId())
            .setPhone(queryUserRequest.getPhone()).setUserCode(queryUserRequest.getUserCode())
            .setUserName(queryUserRequest.getUserName());

        Integer count = userMapper.countUser(listPageUserParam);
        if (Objects.isNull(count) || count < 1) {
            return PageDTO.of(0, Collections.emptyList());
        }

        PageUtil.setSize(listPageUserParam, queryUserRequest);
        List<UserDO> userList = userMapper.listPageUser(listPageUserParam);

        return PageDTO.of(count, userDo2UserDTO(userList));
    }

    @Override
    public void saveUser(SaveUserRequest saveUserRequest) {

        UserDO selectUserParam = new UserDO();
        selectUserParam.setUserCode(saveUserRequest.getUserCode());
        Wrapper<UserDO> queryWrapper = new QueryWrapper<>(selectUserParam);
        UserDO sameUserCodeUserDO = userMapper.selectOne(queryWrapper);
        if (Objects.nonNull(sameUserCodeUserDO)) {
            throw new SystemException("用户编号已存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        selectUserParam.setUserCode(null);
        selectUserParam.setLoginName(saveUserRequest.getLoginName());
        UserDO sameLoginNameUserDO = userMapper.selectOne(queryWrapper);
        if (Objects.nonNull(sameLoginNameUserDO)) {
            throw new SystemException("账号已存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        UserDO saveUserParam = new UserDO();
        saveUserParam.setUserCode(saveUserRequest.getUserCode()).setUserName(saveUserRequest.getUserName())
            .setLoginName(saveUserRequest.getLoginName())
            .setPhone(Optional.ofNullable(saveUserRequest.getPhone()).orElse(StringUtils.EMPTY))
            .setEmail(Optional.ofNullable(saveUserRequest.getEmail()).orElse(StringUtils.EMPTY))
            .setOrganizationId(saveUserRequest.getOrganizationId()).setPswd(initPswd);
        userMapper.insert(saveUserParam);

    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {

        UserDO userDO = userMapper.selectById(updateUserRequest.getId());
        if (Objects.isNull(userDO)) {
            throw new SystemException("用户不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        if (!updateUserRequest.getUserCode().equals(userDO.getUserCode())) {
            UserDO user =
                userMapper.selectOne(new QueryWrapper<>(new UserDO().setUserCode(updateUserRequest.getUserCode())));
            if (Objects.nonNull(user)) {
                throw new SystemException("用户编号已存在", ResponseEnum.BUSINESS_ERROR_CODE);
            }
        }

        UserDO updateUserParam =
            new UserDO().setId(updateUserRequest.getId()).setUserCode(updateUserRequest.getUserCode())
                .setUserName(updateUserRequest.getUserName()).setOrganizationId(updateUserRequest.getOrganizationId())
                .setEmail(Optional.ofNullable(updateUserRequest.getEmail()).orElse(StringUtils.EMPTY))
                .setPhone(Optional.ofNullable(updateUserRequest.getPhone()).orElse(StringUtils.EMPTY));
        userMapper.updateById(updateUserParam);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteUser(Long id) {

        if (Objects.isNull(userMapper.selectById(id))) {
            throw new SystemException("用户不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        userMapper.deleteById(id);

        userRoleMapper.delete(new QueryWrapper<>(new UserRoleDO().setUserId(id)));

        usergroupUserMapper.delete(new QueryWrapper<>(new UsergroupUserDO().setUserId(id)));

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveUserRole(SaveUserRoleRequest saveUserRoleRequest) {

        Long userId = saveUserRoleRequest.getUserId();
        if (Objects.isNull(userMapper.selectById(userId))) {
            throw new SystemException("用户不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        userRoleMapper.delete(new QueryWrapper<>(new UserRoleDO().setUserId(userId)));

        Set<Long> roleIdSet = saveUserRoleRequest.getRoleIdSet();
        List<UserRoleDO> userRoleList = Lists.newArrayListWithCapacity(roleIdSet.size());
        UserRoleDO userRole;
        for (Long roleId : roleIdSet) {
            userRole = new UserRoleDO().setId(IdWorker.getId()).setRoleId(roleId).setUserId(userId);
            userRoleList.add(userRole);
        }
        userRoleMapper.batchSaveUserRole(userRoleList);

    }

    @Override
    public UserDTO getUserByLoginName(String loginName) {
        return userDo2UserDTO(userMapper.getUserByLoginName(loginName));
    }

    @Override
    public void login(LoginRequest loginRequest) {
        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getLoginName(), loginRequest.getPswd());
        // 3.执行登录方法
        subject.login(token);
    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
    }

    private List<UserDTO> userDo2UserDTO(List<UserDO> userList) {
        List<UserDTO> userDtoList = Lists.newArrayListWithCapacity(userList.size());

        UserDTO userDTO;
        for (UserDO user : userList) {
            userDTO = new UserDTO();
            userDTO.setEmail(user.getEmail()).setId(user.getId()).setLoginName(user.getLoginName())
                .setOrganizationId(user.getOrganizationId()).setPhone(user.getPhone()).setUserCode(user.getUserCode())
                .setUserName(user.getUserName()).setOrganizationName(
                    Optional.ofNullable(user.getOrganization()).orElse(new OrganizationDO()).getOrganizationName());
            userDtoList.add(userDTO);
        }
        return userDtoList;
    }

    private UserDTO userDo2UserDTO(UserDO user) {
        if (Objects.isNull(user)) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail()).setId(user.getId()).setLoginName(user.getLoginName())
            .setOrganizationId(user.getOrganizationId()).setPhone(user.getPhone()).setUserCode(user.getUserCode())
            .setUserName(user.getUserName()).setPowerSet(user.getPowerUrlSet()).setRoleSet(user.getRoleCodeSet())
            .setPswd(user.getPswd());

        return userDTO;
    }

}
