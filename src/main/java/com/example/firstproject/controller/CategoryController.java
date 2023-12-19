package com.example.firstproject.controller;

import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.entity.Category;
import com.example.firstproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add_category")
    private ResponseEntity<?> addCategory(@RequestHeader("Authorization") String token,@RequestParam String category ) {
       categoryService.addCategory(token,category);
        return ResponseEntity.ok(new ApiResponse(true,"add category success"));
    }

    @GetMapping("/delete_category")
    private ResponseEntity<?> deleteCategory(@RequestHeader("Authorization") String token,@RequestParam long categoryId) {
        categoryService.deleteCategory(token,categoryId);
        return ResponseEntity.ok(new ApiResponse(true,"category deleted successfully"));
    }

    @PostMapping("/update_category")
    private ResponseEntity<?> updateCategory(@RequestHeader("Authorization") String token,@RequestParam long categoryId, @RequestParam String category) {
        categoryService.updateCategory(token,categoryId, category);
        return ResponseEntity.ok(new ApiResponse(true,"category updated success"));
    }

    @GetMapping("/get_all_category_list")
    private List<Category> getAllCategory(@RequestHeader("Authorization") String token) {
        return categoryService.findAllCategory(token);
    }

}
