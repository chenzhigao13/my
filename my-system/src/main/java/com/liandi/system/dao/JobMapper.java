package com.liandi.system.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.JobDO;

/**
 * job的Mapper接口
 * 
 * @author czg
 * @date 2019/10/24 15:03
 */
@Repository
public interface JobMapper extends BaseMapper<JobDO> {

}
