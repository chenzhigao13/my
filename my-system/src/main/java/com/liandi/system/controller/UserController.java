package com.liandi.system.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.system.controller.request.*;
import com.liandi.system.service.UserService;
import com.liandi.system.service.dto.PageDTO;
import com.liandi.system.service.dto.UserDTO;

/**
 * @author czg
 * @date 2019/7/13 17:09
 * @description 用户controller
 */
@RestController
@RequestMapping("sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/queryUser")
    public PageDTO<UserDTO> queryUser(@Valid @RequestBody QueryUserRequest queryUserRequest) {
        return userService.queryUser(queryUserRequest);
    }

    @PostMapping("/saveUser")
    public void saveUser(@Valid @RequestBody SaveUserRequest saveUserRequest) {
        userService.saveUser(saveUserRequest);
    }

    @PostMapping("/updateUser")
    public void updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateUser(updateUserRequest);
    }

    @RequiresPermissions("user:deleteUser")
    @GetMapping("/deleteUser/{userId}")
    public void deleteUser(@NotNull(message = "ID不能为空") @PathVariable("userId") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/saveUserRole")
    public void saveRoleUser(@Valid @RequestBody SaveUserRoleRequest SaveUserRoleRequest) {
        userService.saveUserRole(SaveUserRoleRequest);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest loginRequest) {
        userService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout() {
        userService.logout();
    }

}
