package com.liandi.system.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.liandi.system.controller.request.QueryJobRequest;
import com.liandi.system.controller.request.SaveJobRequest;
import com.liandi.system.controller.request.UpdateJobRequest;
import com.liandi.system.dao.JobMapper;
import com.liandi.system.dao.domain.JobDO;
import com.liandi.system.dao.param.ListPageJobParam;
import com.liandi.system.exception.SystemException;
import com.liandi.system.job.JobEntity;
import com.liandi.system.job.ScheduleJobUtil;
import com.liandi.system.response.PageDTO;
import com.liandi.system.response.ResponseEnum;
import com.liandi.system.service.JobService;
import com.liandi.system.service.dto.JobDTO;
import com.liandi.system.util.PageUtil;

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
    public PageDTO<JobDTO> queryJob(QueryJobRequest queryJobRequest) {
        ListPageJobParam listPageJobParam = new ListPageJobParam();
        listPageJobParam.setBeanName(queryJobRequest.getBeanName());
        listPageJobParam.setGroupName(queryJobRequest.getGroupName());
        listPageJobParam.setJobName(queryJobRequest.getJobName());
        listPageJobParam.setStatus(queryJobRequest.getStatus());

        Integer count = jobMapper.countJob(listPageJobParam);
        if (Objects.isNull(count) || count < 1) {
            return PageDTO.of(0, Collections.emptyList());
        }

        PageUtil.setSize(listPageJobParam, queryJobRequest);

        return PageDTO.of(count, JobDo2JobDTO(jobMapper.listPageJob(listPageJobParam)));
    }

    private List<JobDTO> JobDo2JobDTO(List<JobDO> jobList) {
        List<JobDTO> jobDtoList = Lists.newArrayListWithCapacity(jobList.size());
        JobDTO jobDTO;
        for (JobDO job : jobList) {
            jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setBeanName(job.getBeanName());
            jobDTO.setCron(job.getCron());
            jobDTO.setDescription(job.getDescription());
            jobDTO.setGroupName(job.getGroupName());
            jobDTO.setJobName(job.getJobName());
            jobDTO.setStatus(job.getStatus());

            jobDtoList.add(jobDTO);
        }

        return jobDtoList;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveJob(SaveJobRequest saveJobRequest) {
        JobDO saveJobParam = new JobDO();
        saveJobParam.setBeanName(saveJobRequest.getBeanName());
        saveJobParam.setCron(saveJobRequest.getCron());
        saveJobParam.setDescription(saveJobRequest.getDescription());
        saveJobParam.setGroupName(saveJobRequest.getGroupName());
        saveJobParam.setJobName(saveJobRequest.getJobName());
        saveJobParam.setStatus(saveJobRequest.getStatus());
        saveJobParam.setCreateTime(new Date());
        jobMapper.insert(saveJobParam);

        ScheduleJobUtil.createScheduleJob(scheduler, jobDo2JobEntity(saveJobParam));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateJob(UpdateJobRequest updateJobRequest) {
        JobDO job = getJobDO(updateJobRequest.getId());

        JobDO updateJobParam = new JobDO();
        updateJobParam.setId(updateJobRequest.getId());
        updateJobParam.setBeanName(updateJobRequest.getBeanName());
        updateJobParam.setCron(updateJobRequest.getCron());
        updateJobParam.setDescription(updateJobRequest.getDescription());
        updateJobParam.setGroupName(updateJobRequest.getGroupName());
        updateJobParam.setJobName(updateJobRequest.getJobName());
        jobMapper.updateById(updateJobParam);

        JobEntity jobEntity = new JobEntity();
        jobEntity.setId(updateJobRequest.getId());
        jobEntity.setGroupName(updateJobRequest.getGroupName());
        jobEntity.setCron(updateJobRequest.getCron());
        jobEntity.setBeanName(updateJobRequest.getBeanName());
        jobEntity.setJobName(updateJobRequest.getJobName());
        jobEntity.setStatus(job.getStatus());

        ScheduleJobUtil.updateScheduleJob(scheduler, jobEntity);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteJob(Long jobId) {
        getJobDO(jobId);

        jobMapper.deleteById(jobId);

        ScheduleJobUtil.deleteScheduleJob(scheduler, jobId);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void pauseJob(Long jobId) {
        JobDO job = getJobDO(jobId);

        if (StringUtils.equals(JobEntity.PAUSE, job.getStatus())) {
            throw new SystemException("该任务已暂停，无须重复操作", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        JobDO updateJobParam = new JobDO();
        updateJobParam.setId(jobId);
        updateJobParam.setStatus(JobEntity.PAUSE);
        jobMapper.updateById(updateJobParam);

        ScheduleJobUtil.pauseJob(scheduler, jobId);
    }

    @Override
    public void runJob(Long jobId) {
        JobDO job = getJobDO(jobId);

        ScheduleJobUtil.run(scheduler, jobDo2JobEntity(job));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void resumeJob(Long jobId) {
        JobDO job = getJobDO(jobId);

        if (StringUtils.equals(JobEntity.PAUSE, job.getStatus())) {
            throw new SystemException("该任务已正常，无须重复操作", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        JobDO updateJobParam = new JobDO();
        updateJobParam.setId(jobId);
        updateJobParam.setStatus(JobEntity.NORMAL);
        jobMapper.updateById(updateJobParam);

        ScheduleJobUtil.resumeJob(scheduler, jobId);
    }

    private JobDO getJobDO(Long jobId) {
        JobDO job = jobMapper.selectById(jobId);
        if (Objects.isNull(job)) {
            throw new SystemException("任务ID不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }
        return job;
    }

    private JobEntity jobDo2JobEntity(JobDO job) {
        JobEntity jobEntity = new JobEntity();
        jobEntity.setId(job.getId());
        jobEntity.setJobName(job.getJobName());
        jobEntity.setBeanName(job.getBeanName());
        jobEntity.setCron(job.getCron());
        jobEntity.setGroupName(job.getGroupName());
        jobEntity.setStatus(job.getStatus());

        return jobEntity;
    }

}
