package com.liandi.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.RoleOrganizationDO;

/**
 * @author czg
 * @date 2019/8/15 16:00
 * @description 角色与组织关系Mapper接口
 */
@Repository
public interface RoleOrganizationMapper extends BaseMapper<RoleOrganizationDO> {}
