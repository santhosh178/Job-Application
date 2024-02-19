package com.example.firstproject.service;

import com.example.firstproject.dto.JobDTO;

import java.time.ZonedDateTime;
import java.util.List;

public interface JobDetailsService {

    List<JobDTO> findByStatus(String token,String status,ZonedDateTime lastModifiedTimeString,Long limit);

    List<JobDTO> findByUserId(String token,Long lastJobId,Long limit);

    List<JobDTO> findAll(String token,ZonedDateTime lastModifiedTimeString,Long limit);

    List<JobDTO> findAllBeforeId(String token, Long limit);

    List<JobDTO> findByUserIdDetails(String token, ZonedDateTime lastModifiedTime, Long limit);

    List<JobDTO> findAllBeforeUserId(String token, Long limit);

}
