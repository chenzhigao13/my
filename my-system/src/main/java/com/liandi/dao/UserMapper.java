package com.liandi.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.dao.domain.UserDO;
import com.liandi.dao.param.ListPageUserParam;
import com.liandi.dao.param.ListUserParam;

/**
 * @author czg
 * @date 2019/7/16 9:59
 * @description 用户Mapper接口
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

}
