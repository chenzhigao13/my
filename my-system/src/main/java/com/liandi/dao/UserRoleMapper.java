package com.liandi.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.UserRoleDO;

/**
 * @author czg
 * @date 2019/7/25 13:45
 * @description 用户与角色关联Dao接口
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {}
