package com.example.firstproject.repository;

import com.example.firstproject.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsByImageName(String imageName);
}
