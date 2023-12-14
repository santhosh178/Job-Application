package com.example.firstproject.controller;

import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.dto.JobRequest;
import com.example.firstproject.entity.Job;
import com.example.firstproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/add_job")
    public ResponseEntity<?> addJob(@RequestHeader("Authorization") String token,@RequestBody JobRequest jobRequest) {
        Job job = jobService.addJob(token,jobRequest);
        return ResponseEntity.ok(new ApiResponse(true,"Job added successfully"));
    }

    @GetMapping("/get_job")
    public List<Job> getJob(@RequestHeader("Authorization") String token) {
        return jobService.getJobDetails(token);
    }

    @PostMapping("/update_job_assigner")
    public ResponseEntity<?> uploadJobAssigner(@RequestHeader("Authorization") String token,@RequestParam long jobId) {
        Job job = jobService.updateJob(jobId,token);
        return ResponseEntity.ok(new ApiResponse(true,"picked job success"));
    }

    @GetMapping("/get_all_jobs")
    public List<Job> getAllJobs() {
        return jobService.findAllJobs();
    }

    @PostMapping("/add_closing_time")
    public ResponseEntity<?> addClosingTime(@RequestHeader("Authorization") String token,@RequestParam long jobId) {
        Job job = jobService.addClosingTime(jobId,token);
        return ResponseEntity.ok(new ApiResponse(true,"Job finish success"));
    }

}
