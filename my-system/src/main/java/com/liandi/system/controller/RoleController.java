package com.liandi.system.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.system.controller.request.SaveRolePowerOrganizationRequest;
import com.liandi.system.controller.request.SaveRoleRequest;
import com.liandi.system.controller.request.UpdateRoleRequest;
import com.liandi.system.service.RoleService;

/**
 * @author czg
 * @date 2019/8/4 19:47
 * @description 角色controller
 */
@RestController
@RequestMapping("sys/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/saveRole")
    public void saveRole(@Valid @RequestBody SaveRoleRequest saveRoleRequest) {
        roleService.saveRole(saveRoleRequest);
    }

    @PostMapping("/updateRole")
    public void updateRole(@Valid @RequestBody UpdateRoleRequest updateRoleRequest) {
        roleService.updateRole(updateRoleRequest);
    }

    @GetMapping("/deleteRole/{roleId}")
    public void deleteUser(@NotNull(message = "ID不能为空") @PathVariable("roleId") Long id) {
        roleService.deleteRole(id);
    }

    @PostMapping("/saveRolePowerAndOrganization")
    public void saveRolePowerAndOrganization(
        @Valid @RequestBody SaveRolePowerOrganizationRequest saveRolePowerOrganizationRequest) {
        roleService.saveRolePowerAndOrganization(saveRolePowerOrganizationRequest);
    }

}
