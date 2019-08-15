package com.liandi.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.liandi.MySystemApplicationTests;
import com.liandi.controller.request.SavePowerRequest;
import com.liandi.service.PowerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PowerServiceImplTest extends MySystemApplicationTests {

    @Autowired
    private PowerService powerService;

    @Test
    public void testQueryPowerTree() {
        log.info("queryPowerTree：{}", new Gson().toJson(powerService.queryPowerTree()));
    }

    @Test
    public void testSavePower() {

        SavePowerRequest savePowerRequest = new SavePowerRequest();

        for (int i = 0; i < 100; i++) {

            savePowerRequest.setParentPowerId(3L);
            savePowerRequest.setPowerName("common_" + (i + 1));
            savePowerRequest.setPowerType("1");
            savePowerRequest.setPowerUrl("#");
            savePowerRequest.setSort(1);
            powerService.savePower(savePowerRequest);

        }

    }

}