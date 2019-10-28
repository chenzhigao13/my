package com.liandi.system.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.PowerDO;

/**
 * 权限Mapper接口
 * 
 * @author czg
 * @date 2019/7/25 10:17
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

    /**
     * 查询权限
     * 
     * @param id
     * @return
     */
    PowerDO getPower(@Param("id") Long id);

    /**
     * 查询权限url
     * 
     * @param userId
     * @return
     */
    Set<String> listPowerUrl(@Param("userId") Long userId);

}
