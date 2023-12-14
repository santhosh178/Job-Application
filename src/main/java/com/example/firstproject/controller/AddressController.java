package com.example.firstproject.controller;

import com.example.firstproject.dto.AddressRequest;
import com.example.firstproject.dto.ApiResponse;
import com.example.firstproject.entity.Address;
import com.example.firstproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")

public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add_address")
    public ResponseEntity<?> addAddress(@RequestHeader("Authorization") String token,@RequestBody AddressRequest addressRequest) {
        Address address = addressService.addAddress(token,addressRequest);
        return ResponseEntity.ok(new ApiResponse(true,"add Address success"));
    }

    @GetMapping("/delete_address")
    private ResponseEntity<?> deleteAddress(@RequestHeader("Authorization") String token,@RequestParam long addressId) {
        addressService.deleteAddress(token,addressId);
        return ResponseEntity.ok(new ApiResponse(true,"Address deleted successfully"));
    }

    @PostMapping("/update_address")
    public ResponseEntity<?> updateAddress(@RequestHeader("Authorization") String token,@RequestParam long addressId, @RequestBody AddressRequest addressRequest) {
        Address address  = addressService.updateAddress(token,addressId, addressRequest);
        return ResponseEntity.ok(new ApiResponse(true,"Address updated success"));
    }

    @GetMapping("/get_address")
    public List<Address> getAddress(@RequestHeader("Authorization") String token) {
        return addressService.getAddress(token);
    }

}
