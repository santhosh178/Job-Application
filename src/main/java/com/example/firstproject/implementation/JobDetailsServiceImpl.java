package com.example.firstproject.implementation;

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

    @Override
    public List<JobDTO> findByStatus(String token,String status) {
        Long userId = tokenProvider.extractUserId(token);
        if( userId != 0L) {
            List<Job> job = jobRepository.findByStatus(status);
            return job.stream().map(jobMapper::toJobDTO).collect(Collectors.toList());
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

    @Override
    public List<JobDTO> findByUserId(String token) {
        Long userId = tokenProvider.extractUserId(token);
        List<Job> job = jobRepository.findByUserId(userId);
        return job.stream().map(jobMapper::toJobDTO).collect(Collectors.toList());
    }

}
