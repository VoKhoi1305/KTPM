package com.bluemoon.management.bluemoon.service.impl;

import com.bluemoon.management.bluemoon.dto.UserDTO;
import com.bluemoon.management.bluemoon.entity.User;
import com.bluemoon.management.bluemoon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    private UserDTO convertEntityToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setFullName(user.getFullname());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getUserRole());
        userDTO.setPassword(user.getHashPassword());
        userDTO.setAccountStatus(user.getAccountStatus());
        userDTO.setAvatarURL(user.getAvatarUrl());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

}
