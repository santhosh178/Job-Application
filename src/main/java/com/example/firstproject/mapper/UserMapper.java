package com.example.firstproject.mapper;

import com.example.firstproject.dto.UserDTO;
import com.example.firstproject.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setImage_id(user.getImageId());
        return userDTO;
    }

}
