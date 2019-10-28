package com.liandi.system.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.UsergroupRoleDO;

/**
 * 用户组与角色关联Mapper接口
 * 
 * @author czg
 * @date 2019/7/25 13:53
 */
@Repository
public interface UsergroupRoleMapper extends BaseMapper<UsergroupRoleDO> {}
