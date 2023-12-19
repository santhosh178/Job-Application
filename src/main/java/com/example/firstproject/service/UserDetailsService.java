package com.example.firstproject.service;

import com.example.firstproject.dto.UserDTO;

public interface UserDetailsService{
    UserDTO findById(String token);

}