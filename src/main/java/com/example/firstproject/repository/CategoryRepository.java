package com.example.firstproject.repository;

import com.example.firstproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);

}
