package com.liandi.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.UserRoleDO;

/**
 * @author czg
 * @date 2019/7/25 13:45
 * @description 用户与角色关联Mapper接口
 */
@Repository
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {

    /**
     * 批量保存用户角色关系
     * 
     * @param userRoleList
     */
    void batchSaveUserRole(@Param("userRoleList") List<UserRoleDO> userRoleList);

}
