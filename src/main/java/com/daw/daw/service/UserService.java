package com.daw.daw.service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.daw.daw.model.User;
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
    public Collection <UserDTO> getAllUsers() {
        return toDTOs(userRepository.findAll());
    }

    private Collection<UserDTO> toDTOs(Collection <User> users) {
		return userMapper.toDTOs(users);
	}
}
