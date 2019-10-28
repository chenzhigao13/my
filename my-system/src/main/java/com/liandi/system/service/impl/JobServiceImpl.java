package com.liandi.system.service.impl;

import javax.annotation.PostConstruct;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.liandi.system.constant.JobStatusEnum;
import com.liandi.system.controller.request.SaveJobRequest;
import com.liandi.system.dao.JobMapper;
import com.liandi.system.job.JobEntity;
import com.liandi.system.service.JobService;
import com.liandi.system.util.ScheduleUtil;

/**
 * job的Service接口实现
 * 
 * @author czg
 * @date 2019/10/25 14:55
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobMapper jobMapper;

    @Override
    public void saveJob(SaveJobRequest saveJobRequest) {

    }

    @PostConstruct
    public void test() {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setStatus(JobStatusEnum.NORMAL.getStatus());
        jobEntity.setId(IdWorker.getId());
        jobEntity.setGroupName("testGroup");
        jobEntity.setDescription("");
        jobEntity.setCron("0/10 * * * * ?");
        jobEntity.setBeanName("testTask");
        jobEntity.setJobName("");

        ScheduleUtil.createScheduleJob(scheduler, jobEntity);

    }

}
