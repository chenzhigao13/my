package com.liandi.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.RoleDO;

/**
 * @author czg
 * @date 2019/7/19 10:57
 * @description 角色Mapper接口
 */
@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {

    /**
     * 根据用户ID查询角色
     * 
     * @param userId
     * @return
     */
    List<RoleDO> listRoleByUserId(@Param("userId") Long userId);

    /**
     * 查询角色编号
     * 
     * @param userId
     * @return
     */
    Set<String> listRoleCode(@Param("userId") Long userId);

}
