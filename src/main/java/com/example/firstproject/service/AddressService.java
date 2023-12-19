package com.example.firstproject.service;

import com.example.firstproject.dto.AddressRequest;
import com.example.firstproject.entity.Address;
import com.example.firstproject.entity.User;
import com.example.firstproject.exception.NotFoundException;
import com.example.firstproject.repository.AddressRepository;
import com.example.firstproject.repository.UserRepository;
import com.example.firstproject.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    public Address addAddress(String token,AddressRequest addressRequest) {
        Long userId = tokenProvider.extractUserId(token);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        if (addressRepository.existsByStreetAddress(addressRequest.getStreetAddress())) {
            throw new NotFoundException("Address already exists");
        }

        Address address = new Address();
        address.setStreetAddress(addressRequest.getStreetAddress());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setPinCode(addressRequest.getPinCode());
        address.setCountry(addressRequest.getCountry());
        address.setLatitude(addressRequest.getLatitude());
        address.setLongitude(addressRequest.getLongitude());
        address.setAddedTime(ZonedDateTime.now());
        address.setModifiedTime(ZonedDateTime.now());
        address.setUser(user);

        return addressRepository.save(address);
    }

    public void deleteAddress(String token,long addressId) {
        Long userId = tokenProvider.extractUserId(token);

        Optional<User> user =userRepository.findById(userId);

        if(user.isPresent()) {
            User existingUser = user.get();

            if(Objects.equals(existingUser.getRole(), "ADMIN")) {
                Address address = addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException("Address id not match"));
                addressRepository.deleteById(address.getId());
            }
            else {
                throw new NotFoundException("Admin only to access the category");
            }

        }
        else {
            throw new NotFoundException("User id not match");
        }
    }

    public Address updateAddress(String token,long addressId, AddressRequest addressRequest) {
        Long userId = tokenProvider.extractUserId(token);
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User id not match"));
        Optional<Address> address = addressRepository.findById(addressId);

        if (address.isPresent()) {
            Address existingAddress = address.get();

            if (addressRequest.getStreetAddress() != null) {
                existingAddress.setStreetAddress(addressRequest.getStreetAddress());
            }
            if (addressRequest.getCity() != null) {
                existingAddress.setCity(addressRequest.getCity());
            }
            if (addressRequest.getState() != null) {
                existingAddress.setState(addressRequest.getState());
            }
            if (addressRequest.getCountry() != null) {
                existingAddress.setCountry(addressRequest.getCountry());
            }
            if (addressRequest.getLatitude() != null) {
                existingAddress.setLatitude(addressRequest.getLatitude());
            }
            if (addressRequest.getLongitude() != null) {
                existingAddress.setLongitude(addressRequest.getLongitude());
            }
            if (addressRequest.getPinCode() != 0L) {
                existingAddress.setPinCode(addressRequest.getPinCode());
            }
            existingAddress.setModifiedTime(ZonedDateTime.now());

            return addressRepository.save(existingAddress);

        } else {
            throw new NotFoundException("Address id not match");
        }
    }

}
