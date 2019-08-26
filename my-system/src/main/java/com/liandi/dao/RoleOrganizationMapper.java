package com.liandi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.RoleOrganizationDO;

/**
 * @author czg
 * @date 2019/8/15 16:00
 * @description 角色与组织关系Mapper接口
 */
@Repository
public interface RoleOrganizationMapper extends BaseMapper<RoleOrganizationDO> {

    /**
     * 批量保存角色与组织关系
     * 
     * @param roleOrganizationList
     */
    void batchSaveRoleOrganization(@Param("roleOrganizationList") List<RoleOrganizationDO> roleOrganizationList);
}
