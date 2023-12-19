package com.example.firstproject.implementation;

import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.UserMapper;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import com.example.firstproject.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenProvider tokenProvider;


    @Override
    public UserDTO findById(String token) {
        Long id = tokenProvider.extractUserId(token);
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            User users = user.get();
        return userMapper.toUserDTO(users);
        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

}
