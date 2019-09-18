package com.liandi.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.controller.request.*;
import com.liandi.service.UserService;
import com.liandi.service.dto.PageDTO;
import com.liandi.service.dto.UserDTO;

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
        // TODO

        // 1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getLoginName(), loginRequest.getPswd());
        // 3.执行登录方法
        subject.login(token);
    }

    @PostMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
    }

}
