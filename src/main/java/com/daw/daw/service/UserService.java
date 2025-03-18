package com.daw.daw.service;

import java.util.Collection;
import com.daw.daw.security.CSRFHandlerConfiguration;

import org.mapstruct.control.MappingControl.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.daw.daw.model.User;
import com.daw.daw.controller.MVC.ComentsMVCController;
import com.daw.daw.dto.*;
import com.daw.daw.repository.UserRepository;

@Service
public class UserService {


    private final DaoAuthenticationProvider authenticationProvider;

    private final PasswordEncoder passwordEncoder;

    private final CreateUserMapperImpl createUserMapperImpl;

    private final AuthenticationManager authenticationManager;

    private final CSRFHandlerConfiguration CSRFHandlerConfiguration;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CreateUserMapper createUserMapper;

    private User toDomain(CreateRequestUserDTO createRequestUserDTO) {
        return createUserMapper.toDomain(createRequestUserDTO);
    }

    UserService(CSRFHandlerConfiguration CSRFHandlerConfiguration, AuthenticationManager authenticationManager, CreateUserMapperImpl createUserMapperImpl, PasswordEncoder passwordEncoder, DaoAuthenticationProvider authenticationProvider) {
        this.authenticationManager = authenticationManager;
        this.CSRFHandlerConfiguration = CSRFHandlerConfiguration;
        this.createUserMapperImpl = createUserMapperImpl;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }

    public UserDTO getMe(String name) {
        return userMapper.toDTO(userRepository.findByName(name).orElseThrow());
    }

    public Collection<UserDTO> getAllUsers() {
        return toDTOs(userRepository.findAll());
    }

    private Collection<UserDTO> toDTOs(Collection<User> users) {
        return userMapper.toDTOs(users);
    }

    public UserDTO getUser(long id) {
        return userMapper.toDTO(userRepository.findById(id).orElseThrow());
    }

    public CreateRequestUserDTO createUser(CreateRequestUserDTO createRequestUserDTO) {
        if (createRequestUserDTO.id() != null) {
            throw new IllegalArgumentException("id must be null");
        }
        User user = toDomain(createRequestUserDTO);
        user.setEncodedPassword(passwordEncoder.encode(createRequestUserDTO.password()));
        userRepository.save(user);
        return createUserMapper.toDTO(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public Collection<UserDTO> deleteAllUsers() {
        Collection<User> users = userRepository.findByRolesContaining("USER");
        userRepository.deleteAll(users);
        return userMapper.toDTOs(users);
    }

    public UserDTO replaceUser (long id, CreateRequestUserDTO updateUserDTO){

        User updateUser = toDomain(updateUserDTO);
        updateUser.setId(id);
        updateUser.setEncodedPassword(passwordEncoder.encode(updateUserDTO.password()));
        userRepository.save(updateUser);
        return userMapper.toDTO(updateUser);
    }
    
}