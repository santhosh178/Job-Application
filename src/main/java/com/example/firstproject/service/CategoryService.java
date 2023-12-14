package com.example.firstproject.service;

import com.example.firstproject.entity.Category;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.repository.CategoryRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    public Category addCategory(String token,String category) {
        Long userId = tokenProvider.extractUserId(token);

        Optional<User> user =userRepository.findById(userId);

        if(user.isPresent()) {
            User existingUser = user.get();
            if(Objects.equals(existingUser.getRole(), "ADMIN")) {
                if (categoryRepository.existsByName(category)){
                    throw new NotFoundException("category already exists");
                }
                Category newCategory = new Category();
                newCategory.setName(category);
                newCategory.setAddedTime(ZonedDateTime.now());
                newCategory.setModifiedTime(ZonedDateTime.now());

                return categoryRepository.save(newCategory);
            }
            else {
                throw new NotFoundException("Admin only to access the category");
            }
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

    public void deleteCategory(String token,long categoryId) {
        Long userId = tokenProvider.extractUserId(token);
        Optional<User> user =userRepository.findById(userId);

        if(user.isPresent()) {
            User existingUser = user.get();

            if(Objects.equals(existingUser.getRole(), "ADMIN")) {
                Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category id not match"));
                categoryRepository.deleteById(category.getId());
            }
            else {
            throw new NotFoundException("Admin only to access the category");
            }
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

    public Category updateCategory(String token,long categoryId, String category) {
        Long userId = tokenProvider.extractUserId(token);
        Optional<User> user =userRepository.findById(userId);

        if(user.isPresent()) {
            User existingUser = user.get();

            if(Objects.equals(existingUser.getRole(), "ADMIN")) {
                if (categoryRepository.existsByName(category)){
                    throw new NotFoundException("category already exists");
                }

                Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category id not match"));
                existingCategory.setName(category);
                existingCategory.setModifiedTime(ZonedDateTime.now());
                return categoryRepository.save(existingCategory);
            }
            else {
                throw new NotFoundException("Admin only to access the category");
            }
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

    public List<Category> findAllCategory(String token) {
        Long userId = tokenProvider.extractUserId(token);
        Optional<User> user =userRepository.findById(userId);

        if(user.isPresent()) {
            User existingUser = user.get();

            if(Objects.equals(existingUser.getRole(), "ADMIN")) {
                return categoryRepository.findAll();
            }
            else {
                throw new NotFoundException("Admin only to access the category");
            }
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

}
