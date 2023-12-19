package com.example.firstproject.repository;

import com.example.firstproject.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    boolean existsByStreetAddress(String streetAddress);

    List<Address> findByUserId(long id);

}
