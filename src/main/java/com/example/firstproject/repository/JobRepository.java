package com.example.firstproject.repository;

import com.example.firstproject.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findByStatus(String status);

    List<Job> findByUserId(Long userId);

    List<Job> findByUserIdAndIdGreaterThanOrderByUserId(Long userId, Long lastJobId);

    List<Job> findByModifiedTimeGreaterThanOrderByModifiedTimeAsc(ZonedDateTime lastModifiedTime);

    List<Job> findAllByOrderByModifiedTimeDesc();

    List<Job> findAllByModifiedTimeLessThanOrderByModifiedTimeDesc(ZonedDateTime lastModifiedTime);

    List<Job> findByUserIdOrderByModifiedTimeDesc(Long userId);

    List<Job> findByUserIdAndModifiedTimeLessThanOrderByModifiedTimeDesc(Long userId, ZonedDateTime lastModifiedTime);

}
