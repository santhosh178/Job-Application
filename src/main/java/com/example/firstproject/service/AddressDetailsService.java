package com.example.firstproject.service;

import com.example.firstproject.dto.AddressDTO;

import java.util.List;

public interface AddressDetailsService {
    List<AddressDTO> findByUserId(String token);

}
