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

    List<Job> findByStatusAndIdGreaterThan(String status, Long lastJobId);

    List<Job> findByUserIdAndIdGreaterThanOrderByUserId(Long userId, Long lastJobId);

    List<Job> findAllByOrderByIdDesc();

    List<Job> findAllByIdLessThanOrderByIdDesc(Long lastJobId);

    List<Job> findByModifiedTimeGreaterThanOrderByModifiedTimeAsc(ZonedDateTime lastModifiedTime);

    List<Job> findAllByOrderByModifiedTimeDesc();

    List<Job> findAllByIdLessThanOrderByModifiedTimeDesc(Long lastJobId);

    List<Job> findAllByModifiedTimeLessThanOrderByModifiedTimeDesc(ZonedDateTime lastModifiedTime);

//    List<Job> findAllByModifiedTimeLessThanOrderByModifiedTimeDesc(Long lastJobId);
}
