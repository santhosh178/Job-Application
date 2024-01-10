package com.example.firstproject.implementation;

import com.example.firstproject.config.AppProperties;
import com.example.firstproject.dto.JobDTO;
import com.example.firstproject.entity.Job;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.JobMapper;
import com.example.firstproject.repository.JobRepository;
import com.example.firstproject.security.TokenProvider;
import com.example.firstproject.service.JobDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobDetailsServiceImpl implements JobDetailsService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private TokenProvider tokenProvider;

    private AppProperties appProperties;

    public JobDetailsServiceImpl(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public List<JobDTO> findByStatus(String token, String status, Long lastJobId,Long limit) {
        Long userId = tokenProvider.extractUserId(token);
        if (userId != 0L) {
            List<Job> job;
            long backendLimit = appProperties.getLimits().getLimit();

            if (lastJobId != null) {
                    // Fetch jobs with IDs greater than the lastJobId
                job = jobRepository.findByStatusAndIdGreaterThan(status, lastJobId);
            } else {
                    // Fetch jobs without considering the lastJobId
                job = jobRepository.findByStatus(status);
            }
            if (lastJobId > backendLimit) {
                throw new NotFoundException("Backend limit exceeded");
            }
            return job.stream().map(jobMapper::toJobDTO).limit(limit).collect(Collectors.toList());
        } else {
            throw new NotFoundException("User id not match");
        }
    }

    @Override
    public List<JobDTO> findByUserId(String token, Long lastJobId, Long limit) {
        Long userId = tokenProvider.extractUserId(token);
        if (userId != 0L) {
            List<Job> jobs;
            long backendLimit = appProperties.getLimits().getLimit();

            if (lastJobId != null) {
                // Fetch all jobs with IDs greater than the lastJobId
                jobs = jobRepository.findByIdGreaterThan(lastJobId);
            } else {
                // Fetch all jobs without considering the lastJobId
                jobs = jobRepository.findByUserId(userId);
            }

            if (lastJobId > backendLimit) {
                throw new NotFoundException("Backend limit exceeded");
            }
            return jobs.stream().map(jobMapper::toJobDTO).limit(limit).collect(Collectors.toList());
        } else {
            throw new NotFoundException("User id not match");
        }
    }

}
