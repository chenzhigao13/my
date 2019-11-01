package com.liandi.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liandi.system.dao.domain.JobDO;
import com.liandi.system.dao.param.ListPageJobParam;

/**
 * job的Mapper接口
 * 
 * @author czg
 * @date 2019/10/24 15:03
 */
@Repository
public interface JobMapper extends BaseMapper<JobDO> {

    /**
     * 查询任务总数
     * 
     * @param listPageJobParam
     * @return
     */
    Integer countJob(ListPageJobParam listPageJobParam);

    /**
     * 分页查询任务
     * 
     * @param listPageJobParam
     * @return
     */
    List<JobDO> listPageJob(ListPageJobParam listPageJobParam);

}
