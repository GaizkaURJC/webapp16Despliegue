package com.daw.daw.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;
import java.util.NoSuchElementException;

import com.daw.daw.security.CSRFHandlerConfiguration;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import com.daw.daw.model.User;
import com.daw.daw.dto.*;
import com.daw.daw.repository.UserRepository;

/**
 * UserService is a Spring Service class that provides various operations
 * related to User management.
 * It includes methods for creating, retrieving, updating, and deleting users,
 * as well as handling user images.
 * This service interacts with the UserRepository to perform database operations
 * and uses mappers to convert between
 * domain objects and DTOs.
 */

@Service
public class UserService {

    private final SecurityFilterChain apiFilterChain;

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

    UserService(CSRFHandlerConfiguration CSRFHandlerConfiguration, AuthenticationManager authenticationManager,
            CreateUserMapperImpl createUserMapperImpl, PasswordEncoder passwordEncoder,
            DaoAuthenticationProvider authenticationProvider, SecurityFilterChain apiFilterChain) {
        this.authenticationManager = authenticationManager;
        this.CSRFHandlerConfiguration = CSRFHandlerConfiguration;
        this.createUserMapperImpl = createUserMapperImpl;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.apiFilterChain = apiFilterChain;
    }

    public UserDTO getMe(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));
        return userMapper.toDTO(user);
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

    public UserDTO replaceUser(long id, CreateRequestUserDTO updateUserDTO) {

        User updateUser = toDomain(updateUserDTO);
        updateUser.setId(id);
        updateUser.setEncodedPassword(passwordEncoder.encode(updateUserDTO.password()));
        userRepository.save(updateUser);
        return userMapper.toDTO(updateUser);
    }

    public void createUserImage(long id, InputStream inputStream, long size) {

        User user = userRepository.findById(id).orElseThrow();
        user.setImage(true);
        user.setImageFile(BlobProxy.generateProxy(inputStream, size));

        userRepository.save(user);
    }

    public Resource getUserImage(long id) throws SQLException {

        User user = userRepository.findById(id).orElseThrow();
        if (user.getImageFile() != null) {

            return new InputStreamResource(user.getImageFile().getBinaryStream());

        } else {
            throw new NoSuchElementException();
        }
    }

    public void replaceUserImage(long id, InputStream inputStream, long size) {

        User user = userRepository.findById(id).orElseThrow();
        if (!user.getImage()) {
            throw new NoSuchElementException();

        }
        user.setImageFile(BlobProxy.generateProxy(inputStream, size));
        userRepository.save(user);
    }

    public void deleteUserImage(long id) {

        User user = userRepository.findById(id).orElseThrow();

        if (!user.getImage()) {
            throw new NoSuchElementException();
        }
        user.setImageFile(null);
        user.setImage(false);

        userRepository.save(user);
    }
}