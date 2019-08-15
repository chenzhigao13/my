package com.liandi.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liandi.MySystemApplicationTests;
import com.liandi.dao.domain.OrganizationDO;

public class OrganizationMapperTest extends MySystemApplicationTests {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Test
    public void testInsert() {

        OrganizationDO organization = new OrganizationDO();
        organization.setOrganizationName("测试机构");
        organization.setParentOrganizationId(null);
        organization.setSort(1);
        organizationMapper.insert(organization);

    }

}