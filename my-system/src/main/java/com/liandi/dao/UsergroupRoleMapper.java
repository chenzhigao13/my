package com.liandi.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.UsergroupRoleDO;

/**
 * @author czg
 * @date 2019/7/25 13:53
 * @description 用户组与角色关联Dao接口
 */
@Repository
public interface UsergroupRoleMapper extends BaseMapper<UsergroupRoleDO> {}
