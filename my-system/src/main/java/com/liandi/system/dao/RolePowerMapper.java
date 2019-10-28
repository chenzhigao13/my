package com.liandi.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.RolePowerDO;

/**
 * 角色与权限关联Mapper接口
 * 
 * @author czg
 * @date 2019/7/25 10:51
 */
@Repository
public interface RolePowerMapper extends BaseMapper<RolePowerDO> {

    /**
     * 批量保存
     * 
     * @param rolePowerList
     */
    void batchSaveRolePower(@Param("rolePowerList") List<RolePowerDO> rolePowerList);

}
