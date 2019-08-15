package com.liandi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.OrganizationDO;
import com.liandi.dao.param.ListPageOrganizationParam;

/**
 * @author czg
 * @date 2019/7/25 10:35
 * @description 组织Mapper接口
 */
@Repository
public interface OrganizationMapper extends BaseMapper<OrganizationDO> {

    /**
     * 查询总数
     * 
     * @param listPageOrganizationParam
     * @return
     */
    Integer countOrganization(ListPageOrganizationParam listPageOrganizationParam);

    /**
     * 分页查询
     * 
     * @param listPageOrganizationParam
     * @return
     */
    List<OrganizationDO> listPageOrganization(ListPageOrganizationParam listPageOrganizationParam);

    /**
     * 查询组织
     * 
     * @param id
     * @return
     */
    OrganizationDO getOrganization(@Param("id") Long id);

}
