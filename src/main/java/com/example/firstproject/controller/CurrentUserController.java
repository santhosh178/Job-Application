package com.example.firstproject.controller;

import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.dto.SignupRequest;
import com.example.firstproject.entity.User;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/user")
@RestController
public class CurrentUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/update_user")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String token, @RequestBody SignupRequest signupRequest) {
        User user = userService.updateUser(token, signupRequest);
        return ResponseEntity.ok(new ApiResponse(true,"User updated success"));
    }

    @GetMapping("/get_all_user_list")
    private List<User> getAllUsers(@RequestHeader("Authorization") String token) {
        return userService.findAllUser(token);
    }

    @PostMapping("/add_image_user")
    public ResponseEntity<?> updateImage(@RequestHeader("Authorization") String token, @RequestParam long imageId) {
        User user = userService.updateImageInUser(token, imageId);
        return ResponseEntity.ok(new ApiResponse(true,"update image success"));
    }

    @GetMapping("/me")
    public User exampleEndpoint(@RequestHeader("Authorization") String token) {
        User user = userService.extractUserId(token);
        return user;
    }

    @PostMapping("/admin/change_role")
    public ResponseEntity<?> changeRole(@RequestHeader("Authorization") String token) {
        userService.changeRole(token);
        return ResponseEntity.ok(new ApiResponse(true,"Updated role to admin"));

    }

}
