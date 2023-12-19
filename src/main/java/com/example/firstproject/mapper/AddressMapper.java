package com.example.firstproject.mapper;

import com.example.firstproject.dto.AddressDTO;
import com.example.firstproject.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreetAddress(address.getStreetAddress());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setLatitude(address.getLatitude());
        addressDTO.setLongitude(address.getLongitude());
        return addressDTO;
    }

}
