package com.daw.daw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.daw.dto.UserDTO;
import com.daw.daw.dto.UserMapper;
import com.daw.daw.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO getUser(String name) {
        return userMapper.toDTO(userRepository.findByName(name).orElseThrow());
    }
}
