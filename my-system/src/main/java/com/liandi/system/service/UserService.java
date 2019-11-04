package com.liandi.system.service;

import com.liandi.system.controller.request.*;
import com.liandi.system.response.PageDTO;
import com.liandi.system.service.dto.UserDTO;

/**
 * @author czg
 * @date 2019/7/16 9:57
 * @description 用户Service接口
 */
public interface UserService {

    /**
     * 分页查询用户
     * 
     * @param queryUserRequest
     * @return
     */
    PageDTO<UserDTO> queryUser(QueryUserRequest queryUserRequest);

    /**
     * 保存用户
     * 
     * @param saveUserRequest
     */
    void saveUser(SaveUserRequest saveUserRequest);

    /**
     * 更新用户
     * 
     * @param updateUserRequest
     */
    void updateUser(UpdateUserRequest updateUserRequest);

    /**
     * 删除用户
     * 
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 保存用户角色关系
     * 
     * @param saveUserRoleRequest
     */
    void saveUserRole(SaveUserRoleRequest saveUserRoleRequest);

    /**
     * 根据登陆名查询用户
     * 
     * @param loginName
     * @return
     */
    UserDTO getUserByLoginName(String loginName);

    /**
     * 登陆
     * 
     * @param loginRequest
     */
    void login(LoginRequest loginRequest);

    /**
     * 登出
     */
    void logout();

}
