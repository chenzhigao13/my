package com.liandi.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.controller.request.QueryUserRequest;
import com.liandi.controller.request.SaveUserRequest;
import com.liandi.controller.request.SaveUserRoleRequest;
import com.liandi.controller.request.UpdateUserRequest;
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

}
