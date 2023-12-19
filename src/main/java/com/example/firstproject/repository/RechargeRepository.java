package com.example.firstproject.repository;

import com.example.firstproject.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge,Long> {

    List<Recharge> findByUserId(long userId);

}
