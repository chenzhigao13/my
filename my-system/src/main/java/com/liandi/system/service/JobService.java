package com.liandi.system.service;

import com.liandi.system.controller.request.QueryJobRequest;
import com.liandi.system.controller.request.SaveJobRequest;
import com.liandi.system.controller.request.UpdateJobRequest;
import com.liandi.system.response.PageDTO;
import com.liandi.system.service.dto.JobDTO;

/**
 * job的Service接口
 * 
 * @author czg
 * @date 2019/10/25 14:55
 */
public interface JobService {

    /**
     * 分页查询任务
     * 
     * @param queryJobRequest
     * @return
     */
    PageDTO<JobDTO> queryJob(QueryJobRequest queryJobRequest);

    /**
     * 保存任务
     * 
     * @param saveJobRequest
     */
    void saveJob(SaveJobRequest saveJobRequest);

    /**
     * 跟新任务
     * 
     * @param updateJobRequest
     */
    void updateJob(UpdateJobRequest updateJobRequest);

    /**
     * 删除任务
     * 
     * @param jobId
     */
    void deleteJob(Long jobId);

    /**
     * 暂停任务
     * 
     * @param jobId
     */
    void pauseJob(Long jobId);

    /**
     * 立即运行任务
     * 
     * @param jobId
     */
    void runJob(Long jobId);

    /**
     * 恢复任务
     * 
     * @param jobId
     */
    void resumeJob(Long jobId);

}
