package com.liandi.system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liandi.system.controller.request.SaveJobRequest;
import com.liandi.system.service.JobService;

/**
 * Job的Controller
 * 
 * @author czg
 * @date 2019/10/28 14:57
 */
@RestController
@RequestMapping("sys/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/saveJob")
    public void saveJob(@Valid @RequestBody SaveJobRequest saveJobRequest) {
        jobService.saveJob(saveJobRequest);
    }

}
