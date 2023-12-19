package com.example.firstproject.repository;

import com.example.firstproject.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findByStatus(String status);

    List<Job> findByUserId(Long userId);

}
