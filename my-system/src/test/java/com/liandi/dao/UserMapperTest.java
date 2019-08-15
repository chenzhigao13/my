package com.liandi.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liandi.MySystemApplicationTests;
import com.liandi.dao.domain.UserDO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserMapperTest extends MySystemApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        UserDO user = new UserDO();
        user.setEmail("admin@163.com");
        user.setUserName("测试");
        user.setOrganizationId(1154286220981997569L);
        user.setPhone("110");
        user.setUserCode("001");
        user.setLoginName("admin");
        user.setPswd("123456");
        userMapper.insert(user);

        log.info("user_id：{}", user.getId());
    }

    @Test
    public void testListUser() {

        List<UserDO> userList = userMapper.listUser(null);

        Assert.assertNotNull(userList.get(0).getOrganization());
    }

    @Test
    public void testSelectCount() {

        Assert.assertNotNull(userMapper.selectCount(new QueryWrapper<>(new UserDO().setId(1000L))));

    }

}