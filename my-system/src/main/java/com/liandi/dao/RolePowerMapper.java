package com.liandi.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.RolePowerDO;

/**
 * @author czg
 * @date 2019/7/25 10:51
 * @description 角色与权限关联Mapper接口
 */
@Repository
public interface RolePowerMapper extends BaseMapper<RolePowerDO> {}
