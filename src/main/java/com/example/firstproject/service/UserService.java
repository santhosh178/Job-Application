package com.example.firstproject.service;

import com.example.firstproject.dto.LoginRequest;
import com.example.firstproject.dto.Role;
import com.example.firstproject.dto.SignupRequest;
import com.example.firstproject.dto.UserStatus;
import com.example.firstproject.entity.Image;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.repository.ImageRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CreditService creditService;

    public User signup(SignupRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new NotFoundException("Email address already in use.");
        }

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(signupRequest.getPassword());
        user.setPhoneNumber(signupRequest.getPhoneNumber());
        user.setImageId(signupRequest.getImageId());
        user.setStatus(UserStatus.active.toString());
        user.setAddedTime(ZonedDateTime.now());
        user.setModifiedTime(ZonedDateTime.now());
        user.setRole(Role.USER.toString());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User users = userRepository.save(user);
        creditService.addCredit(users.getId());

        return users;
    }

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication);
    }

    public User updateUser(String token, SignupRequest signupRequest) {

        Long userId = tokenProvider.extractUserId(token);
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User existinguser = user.get();

            if (signupRequest.getName() != null) {
                existinguser.setName(signupRequest.getName());
            }
            if (signupRequest.getEmail() != null) {
                existinguser.setEmail(signupRequest.getEmail());
            }
            if (signupRequest.getPhoneNumber() != null) {
                existinguser.setPhoneNumber(signupRequest.getPhoneNumber());
            }
            if (signupRequest.getPassword() != null) {
                existinguser.setPassword(signupRequest.getPassword());
            }

            existinguser.setModifiedTime(ZonedDateTime.now());
            existinguser.setPassword(passwordEncoder.encode(existinguser.getPassword()));

            return userRepository.save(existinguser);

        } else {
            throw new NotFoundException("User id not match");
        }

    }

    public List<User> findAllUser(String token) {
        Long userId = tokenProvider.extractUserId(token);
        Optional<User> user =userRepository.findById(userId);

        if(user.isPresent()) {
            User existingUser = user.get();

            if(Objects.equals(existingUser.getRole(), "ADMIN")) {
                return userRepository.findAll();
            }
            else {
                throw new NotFoundException("Admin only to access the category");
            }
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }


    public User updateImageInUser(String token, long imageId) {
        Long userId = tokenProvider.extractUserId(token);
        Optional<User> user = userRepository.findById(userId);
        Image image = imageRepository.findById(imageId).orElseThrow(() -> new NotFoundException("User id not match"));

        if (user.isPresent()) {
            User existingUser = user.get();

            if (imageId != 0L) {
                existingUser.setImageId(image.getId());
            } else {
                throw new NotFoundException("Image id not match");
            }
            return userRepository.save(existingUser);
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

    public User extractUserId(String token) {
        Long userId = tokenProvider.extractUserId(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        return user;
    }

    public User changeRole(String token) {
        Long userId = tokenProvider.extractUserId(token);
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            User existingUser = user.get();

            if(Objects.equals(existingUser.getRole(), "USER")) {
                existingUser.setRole(Role.ADMIN.toString());
            }
            return userRepository.save(existingUser);
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

}
