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

import java.time.ZonedDateTime;
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

//    @Override
//    public List<JobDTO> findByStatus(String token, String status, Long lastJobId,Long limit) {
//        Long userId = tokenProvider.extractUserId(token);
//        if (userId != 0L) {
//            List<Job> job;
//            long backendLimit = appProperties.getLimits().getLimit();
//
//            if (lastJobId != null) {
//                job = jobRepository.findByStatusAndIdGreaterThan(status, lastJobId);
//            } else {
//                job = jobRepository.findByStatus(status);
//            }
//            if (lastJobId > backendLimit) {
//                throw new NotFoundException("Backend limit exceeded");
//            }
//
//            List<JobDTO> jobDTOs = job.stream()
//                    .limit(limit)
//                    .map(jobMapper::toJobDTO)
//                    .collect(Collectors.toList());
//
//            return jobDTOs;
//        } else {
//            throw new NotFoundException("User id not match");
//        }
//    }

    @Override
    public List<JobDTO> findByStatus(String token, String status,ZonedDateTime lastModifiedTime,Long limit) {
        Long userId = tokenProvider.extractUserId(token);
        if (userId != 0L) {
            List<Job> job;
            long backendLimit = appProperties.getLimits().getLimit();

//            if (lastModifiedTime != null) {
////                job = jobRepository.findByStatusAndIdGreaterThan(status, lastModifiedTime);
//            } else {
                job = jobRepository.findByStatus(status);
//            }
//            if (lastModifiedTime > backendLimit) {
//                throw new NotFoundException("Backend limit exceeded");
//            }
//
            List<JobDTO> jobDTOs = job.stream()
                    .limit(limit)
                    .map(jobMapper::toJobDTO)
                    .collect(Collectors.toList());

            return jobDTOs;
        } else {
            throw new NotFoundException("User id not match");
        }
    }

    @Override
    public List<JobDTO> findByUserId(String token, Long lastJobId, Long limit) {
        Long userId = tokenProvider.extractUserId(token);

        if (userId != null) {
            List<Job> jobs;

            if (lastJobId != null) {
                jobs = jobRepository.findByUserIdAndIdGreaterThanOrderByUserId(userId, lastJobId);
            } else {
                jobs = jobRepository.findByUserId(userId);
            }

            if (jobs.size() > limit) {
                throw new NotFoundException("Result size exceeds the specified limit");
            }

            return jobs.stream().map(jobMapper::toJobDTO).collect(Collectors.toList());
        } else {
            throw new NotFoundException("User id not found");
        }
    }

    @Override
    public List<JobDTO> findAll(String token, ZonedDateTime lastModifiedTime, Long limit) {
        Long userId = tokenProvider.extractUserId(token);
        if (userId != 0L) {
            List<Job> jobs;
//            if (lastModifiedTime != null) {
                jobs = jobRepository.findAllByModifiedTimeLessThanOrderByModifiedTimeDesc(lastModifiedTime);
//            } else {
//                throw new NotFoundException("last id not match");
//            }
            return jobs.stream().map(jobMapper::toJobDTO).limit(limit).collect(Collectors.toList());
        } else {
            throw new NotFoundException("User id not match");
        }
    }

    @Override
    public List<JobDTO> findAllBeforeId(String token, Long limit) {
        Long userId = tokenProvider.extractUserId(token);
        if (userId != 0L) {
            List<Job> jobs;
            jobs = jobRepository.findAllByOrderByModifiedTimeDesc();
            return jobs.stream().map(jobMapper::toJobDTO).limit(limit).collect(Collectors.toList());

        } else {
            throw new NotFoundException("User id not match");
        }
    }

}
