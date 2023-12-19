package com.example.firstproject.repository;

import com.example.firstproject.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Long> {

    Optional<Object> findByUserId(long userId);

}
