package com.example.firstproject.implementation;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.entity.Address;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.mapper.AddressMapper;
import com.example.firstproject.repository.AddressRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import com.example.firstproject.service.AddressDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressDetailsServiceImpl implements AddressDetailsService {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressDTO> findByUserId(String token) {
        Long userId = tokenProvider.extractUserId(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));

        List<Address> address = addressRepository.findByUserId(user.getId());
        return address.stream().map(addressMapper::toAddressDTO).collect(Collectors.toList());
    }

}
