package com.liandi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.PowerDO;

/**
 * @author czg
 * @date 2019/7/25 10:17
 * @description 权限Dao接口
 */
@Repository
public interface PowerMapper extends BaseMapper<PowerDO> {

    /**
     * 根据角色ID查询权限
     * 
     * @param roleId
     * @return
     */
    List<PowerDO> listPowerByRoleId(@Param("roleId") Long roleId);

    PowerDO getPower(@Param("id") Long id);

}
