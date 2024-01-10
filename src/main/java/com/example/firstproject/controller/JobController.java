package com.example.firstproject.controller;

import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.dto.JobDTO;
import com.example.firstproject.service.JobDetailsService;
import com.example.firstproject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobDetailsService jobDetailsService;

    @PostMapping("/add_job")
    public ResponseEntity<?> addJob(@RequestHeader("Authorization") String token,
                                    @RequestParam("jobDescription") String jobDescription,
                                    @RequestParam("category_id") long category_id,
                                    @RequestParam("address_id") long address_id,
                                    @RequestParam("mode") String mode,
                                    @RequestParam("payment") long payment,
                                    @RequestParam("jobTime") String jobTime,
                                    @RequestParam(value = "file", required = false) Optional<MultipartFile> file) throws IOException {

        if (file.isPresent()) {
            jobService.addJob(token, jobDescription, category_id, address_id, mode, payment, jobTime, file.get().getOriginalFilename(), file.get().getBytes());
        } else {
            jobService.addJob(token, jobDescription, category_id, address_id, mode, payment, jobTime, null, null);
        }

        return ResponseEntity.ok(new ApiResponse(true, "Job added successfully"));
    }

    @PostMapping("/update_job_assigner")
    public ResponseEntity<?> uploadJobAssigner(@RequestHeader("Authorization") String token,@RequestParam long jobId) {
        jobService.updateJob(jobId,token);
        return ResponseEntity.ok(new ApiResponse(true,"picked job success"));
    }

    @GetMapping("/get_all_jobs")
    public ResponseEntity<List<JobDTO>> getAllJobDetails(@RequestHeader("Authorization") String token, @RequestParam String status,@RequestParam Long lastJobId,@RequestParam Long limit) {
        List<JobDTO> jobDTOList = jobDetailsService.findByStatus(token,status,lastJobId,limit);
        return ResponseEntity.ok(jobDTOList);
    }

    @GetMapping("/get_user_jobs_details")
    public ResponseEntity<List<JobDTO>> getUserJobDetails(@RequestHeader("Authorization") String token,@RequestParam Long lastJobId,@RequestParam Long limit) {
        List<JobDTO> jobDTOList = jobDetailsService.findByUserId(token,lastJobId,limit);
        return ResponseEntity.ok(jobDTOList);
    }

    @PostMapping("/add_closing_time")
    public ResponseEntity<?> addClosingTime(@RequestHeader("Authorization") String token,@RequestParam long jobId) {
        jobService.addClosingTime(jobId,token);
        return ResponseEntity.ok(new ApiResponse(true,"Job finish success"));
    }

}
