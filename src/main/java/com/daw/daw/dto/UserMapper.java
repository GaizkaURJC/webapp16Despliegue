package com.daw.daw.dto;

import org.mapstruct.Mapper;
import com.daw.daw.model.User;

import org.mapstruct.Mapping;
import java.util.Collection;
import java.util.List;

/**
 * This file defines the UserMapper class within the com.daw.daw.dto package.
 * The UserMapper class is responsible for mapping user-related data transfer
 * objects (DTOs)
 * to and from entity models used within the application.
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    List<UserDTO> toDTOs(Collection<User> user);

    @Mapping(target = "imageFile", ignore = true)
    User toDomain(UserDTO userDTO);
}
