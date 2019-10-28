package com.liandi.system.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.liandi.MySystemApplicationTests;
import com.liandi.system.controller.request.SaveOrganizationRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrganizationServiceTest extends MySystemApplicationTests {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void testQueryOrganizationTree() {
        log.info("organizationTree：{}", new Gson().toJson(organizationService.queryOrganizationTree()));
    }

    @Test
    public void testSaveOrganization() {

        SaveOrganizationRequest organization = new SaveOrganizationRequest();
        organization.setParentOrganizationId(2L);
        organization.setOrganizationName("子机构2_");
        organizationService.saveOrganization(organization);

    }

    @Test
    public void testUpdateOrganization() {}

    @Test
    public void testDeleteOrganization() {}
}