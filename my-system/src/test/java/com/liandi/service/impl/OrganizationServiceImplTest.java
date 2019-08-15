package com.liandi.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.liandi.MySystemApplicationTests;
import com.liandi.controller.request.SaveOrganizationRequest;
import com.liandi.service.OrganizationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrganizationServiceImplTest extends MySystemApplicationTests {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void queryOrganizationTree() {
        log.info("organizationTree：{}", new Gson().toJson(organizationService.queryOrganizationTree()));
    }

    @Test
    public void saveOrganization() {

        SaveOrganizationRequest organization = new SaveOrganizationRequest();
        organization.setParentOrganizationId(2L);
        organization.setOrganizationName("子机构2_");
        organizationService.saveOrganization(organization);

    }

    @Test
    public void updateOrganization() {}

    @Test
    public void deleteOrganization() {}
}