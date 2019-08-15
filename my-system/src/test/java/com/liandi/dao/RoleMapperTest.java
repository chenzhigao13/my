package com.liandi.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liandi.MySystemApplicationTests;
import com.liandi.dao.domain.RoleDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest extends MySystemApplicationTests {

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void saveRole() {
        RoleDO role = new RoleDO();
        role.setRoleName("角色名称");
        roleMapper.insert(role);
    }
}