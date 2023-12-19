package com.example.firstproject.service;

import com.example.firstproject.dto.JobDTO;

import java.util.List;

public interface JobDetailsService {

    List<JobDTO> findByStatus(String token,String status);

    List<JobDTO> findByUserId(String token);

}
