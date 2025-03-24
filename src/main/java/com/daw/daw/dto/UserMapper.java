package com.daw.daw.dto;

import org.mapstruct.Mapper;
import com.daw.daw.model.User;

import org.mapstruct.Mapping;
import java.util.Collection;
import java.util.List;

@Mapper (componentModel = "spring")
public interface UserMapper{

    UserDTO toDTO(User user);
    
    List <UserDTO> toDTOs(Collection <User> user);

    @Mapping(target = "imageFile", ignore = true)
    User toDomain(UserDTO userDTO);   
}



