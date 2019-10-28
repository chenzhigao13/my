package com.liandi.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.UserDO;
import com.liandi.system.dao.param.ListPageUserParam;
import com.liandi.system.dao.param.ListUserParam;

/**
 * 用户Mapper接口
 * 
 * @author czg
 * @date 2019/7/16 9:59
 */
@Repository
public interface UserMapper extends BaseMapper<UserDO> {

    /**
     * 查询总数
     * 
     * @param listPageUserParam
     * @return
     */
    Integer countUser(ListPageUserParam listPageUserParam);

    /**
     * 分页查询
     * 
     * @param listPageUserParam
     * @return
     */
    List<UserDO> listPageUser(ListPageUserParam listPageUserParam);

    /**
     * 查询用户
     * 
     * @param listUserParam
     * @return
     */
    List<UserDO> listUser(ListUserParam listUserParam);

    /**
     * 根据登陆名查询用户
     * 
     * @param loginName
     * @return
     */
    UserDO getUserByLoginName(@Param("loginName") String loginName);

}
