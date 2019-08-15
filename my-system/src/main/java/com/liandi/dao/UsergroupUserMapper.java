package com.liandi.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.UsergroupUserDO;

/**
 * @author czg
 * @date 2019/7/25 14:04
 * @description 用户组与用户关联Mapper接口
 */
@Repository
public interface UsergroupUserMapper extends BaseMapper<UsergroupUserDO> {}
