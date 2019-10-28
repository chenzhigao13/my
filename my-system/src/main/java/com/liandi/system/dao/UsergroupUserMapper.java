package com.liandi.system.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.UsergroupUserDO;

/**
 * 用户组与用户关联Mapper接口
 * 
 * @author czg
 * @date 2019/7/25 14:04
 */
@Repository
public interface UsergroupUserMapper extends BaseMapper<UsergroupUserDO> {}
