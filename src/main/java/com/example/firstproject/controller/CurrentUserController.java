package com.example.firstproject.controller;

import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.dto.SignupRequest;
import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.entity.User;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.service.UserDetailsService;
import com.example.firstproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class CurrentUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/update_user")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String token, @RequestBody SignupRequest signupRequest) {
        userService.updateUser(token, signupRequest);
        return ResponseEntity.ok(new ApiResponse(true,"User updated success"));
    }

    @GetMapping("/get_all_user_list")
    private List<User> getAllUsers(@RequestHeader("Authorization") String token) {
        return userService.findAllUser(token);
    }

    @PostMapping("/add_image_user")
    public ResponseEntity<?> updateImage(@RequestHeader("Authorization") String token,@RequestParam("file") MultipartFile file) throws IOException {
        userService.updateImageInUser(token, file);
        return ResponseEntity.ok(new ApiResponse(true,"update image success"));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> userDetails(@RequestHeader("Authorization") String token) {
        UserDTO userDTO = userDetailsService.findById(token);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/admin/change_role")
    public ResponseEntity<?> changeRole(@RequestHeader("Authorization") String token) {
        userService.changeRole(token);
        return ResponseEntity.ok(new ApiResponse(true,"Updated role to admin"));
    }

}
