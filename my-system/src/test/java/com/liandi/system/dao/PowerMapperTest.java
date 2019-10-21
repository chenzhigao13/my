package com.liandi.system.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liandi.MySystemApplicationTests;
import com.liandi.system.dao.domain.PowerDO;

public class PowerMapperTest extends MySystemApplicationTests {

    @Autowired
    private PowerMapper powerMapper;

    @Test
    public void testInsert() {
        PowerDO power = new PowerDO();
        powerMapper.insert(power);
    }

}