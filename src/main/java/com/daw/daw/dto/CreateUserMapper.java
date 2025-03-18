package com.daw.daw.dto;

import org.mapstruct.Mapper;
import com.daw.daw.model.User;

import org.mapstruct.Mapping;
import java.util.Collection;
import java.util.List;


@Mapper (componentModel = "spring") 
public interface CreateUserMapper {
    
    CreateRequestUserDTO toDTO(User user);
    List <CreateRequestUserDTO> toDTOs(Collection <User> user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "encodedPassword", source = "password")
    User toDomain(CreateRequestUserDTO userDTO);

}
