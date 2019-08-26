package com.liandi.service;

import com.liandi.controller.request.SaveRolePowerOrganizationRequest;
import com.liandi.controller.request.SaveRoleRequest;
import com.liandi.controller.request.UpdateRoleRequest;

/**
 * @author czg
 * @date 2019/7/19 11:25
 * @description 角色Service接口
 */
public interface RoleService {

    /**
     * 保存角色
     * 
     * @param saveRoleRequest
     */
    void saveRole(SaveRoleRequest saveRoleRequest);

    /**
     * 更新角色
     * 
     * @param updateRoleRequest
     */
    void updateRole(UpdateRoleRequest updateRoleRequest);

    /**
     * 删除角色
     * 
     * @param id
     */
    void deleteRole(Long id);

    /**
     * 保存角色权限和组织关系
     * 
     * @param saveRolePowerOrganizationRequest
     */
    void saveRolePowerAndOrganization(SaveRolePowerOrganizationRequest saveRolePowerOrganizationRequest);

}
