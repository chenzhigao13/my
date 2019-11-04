package com.liandi.system.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liandi.MySystemApplicationTests;
import com.liandi.system.controller.request.QueryJobRequest;
import com.liandi.system.response.PageDTO;
import com.liandi.system.service.dto.JobDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JobServiceTest extends MySystemApplicationTests {

    @Autowired
    private JobService jobService;

    @Test
    public void testQueryJob() {
        QueryJobRequest queryJobRequest = new QueryJobRequest();
        queryJobRequest.setCurrentPage(1);
        queryJobRequest.setPageSize(15);
        PageDTO<JobDTO> jobPageDTO = jobService.queryJob(queryJobRequest);
        log.debug("queryJob: {}", jobPageDTO);
    }

    @Test
    public void testSaveJob() {

    }

}