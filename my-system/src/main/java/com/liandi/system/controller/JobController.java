package com.liandi.system.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.system.controller.request.QueryJobRequest;
import com.liandi.system.controller.request.SaveJobRequest;
import com.liandi.system.controller.request.UpdateJobRequest;
import com.liandi.system.service.JobService;
import com.liandi.system.service.dto.JobDTO;

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

    @PostMapping("/queryJob")
    public Pair<Integer, List<JobDTO>> queryJob(@Valid @RequestBody QueryJobRequest queryJobRequest) {
        return jobService.queryJob(queryJobRequest);
    }

    @PostMapping("/saveJob")
    public void saveJob(@Valid @RequestBody SaveJobRequest saveJobRequest) {
        jobService.saveJob(saveJobRequest);
    }

    @PostMapping("/updateJob")
    public void updateJob(@Valid @RequestBody UpdateJobRequest updateJobRequest) {
        jobService.updateJob(updateJobRequest);
    }

    @GetMapping("/deleteJob/{jobId}")
    public void deleteJob(@NotNull(message = "任务ID不能为空") @PathVariable("jobId") Long jobId) {
        jobService.deleteJob(jobId);
    }

    @GetMapping("/pauseJob/{jobId}")
    public void pauseJob(@NotNull(message = "任务ID不能为空") @PathVariable("jobId") Long jobId) {
        jobService.pauseJob(jobId);
    }

    @GetMapping("/runJob/{jobId}")
    public void runJob(@NotNull(message = "任务ID不能为空") @PathVariable("jobId") Long jobId) {
        jobService.runJob(jobId);
    }

    @GetMapping("/resumeJob/{jobId}")
    public void resumeJob(@NotNull(message = "任务ID不能为空") @PathVariable("jobId") Long jobId) {
        jobService.resumeJob(jobId);
    }

}
