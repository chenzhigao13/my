package com.liandi.system.service;

import com.liandi.system.controller.request.SaveJobRequest;

/**
 * job的Service接口
 * 
 * @author czg
 * @date 2019/10/25 14:55
 */
public interface JobService {

    /**
     * 保存任务
     * 
     * @param saveJobRequest
     */
    void saveJob(SaveJobRequest saveJobRequest);

}
