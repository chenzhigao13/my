package com.liandi.system.service;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.liandi.MySystemApplicationTests;
import com.liandi.system.controller.request.QueryJobRequest;
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
        Pair<Integer, List<JobDTO>> jobPair = jobService.queryJob(queryJobRequest);
        log.debug("queryJob: {}", jobPair);
    }

    @Test
    public void testSaveJob() {

    }

}