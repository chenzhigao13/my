package com.liandi.system.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liandi.MySystemApplicationTests;
import com.liandi.system.controller.request.QueryUserRequest;
import com.liandi.system.controller.request.SaveUserRequest;
import com.liandi.system.controller.request.UpdateUserRequest;
import com.liandi.system.service.UserService;
import com.liandi.system.service.dto.PageDTO;
import com.liandi.system.service.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImplTest extends MySystemApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testQueryUser() {
        QueryUserRequest queryUserRequest = new QueryUserRequest();
        queryUserRequest.setCurrentPage(1);
        queryUserRequest.setPageSize(15);
        PageDTO<UserDTO> userPageDTO = userService.queryUser(queryUserRequest);
        log.info("userPageDTO：{}", userPageDTO);
    }

    @Test
    public void testSaveUser() {
        SaveUserRequest saveUserRequest = new SaveUserRequest();
        saveUserRequest.setEmail("email");
        saveUserRequest.setOrganizationId(1154286220981997569L);
        saveUserRequest.setPhone("phone");
        saveUserRequest.setUserCode("009");
        saveUserRequest.setLoginName("admin9");
        saveUserRequest.setUserName("admin");
        userService.saveUser(saveUserRequest);
    }

    @Test
    public void testUpdateUser() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setId(1152115416898965505L);
        updateUserRequest.setOrganizationId(1154286220981997569L);
        updateUserRequest.setPhone("17688990098");
        updateUserRequest.setUserCode("009");
        updateUserRequest.setUserName("admin9");
        userService.updateUser(updateUserRequest);
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(1152115881644539905L);
    }

}