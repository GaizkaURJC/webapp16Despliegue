package com.daw.daw.dto;

import org.mapstruct.Mapper;
import com.daw.daw.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Collection;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper (componentModel = "spring")
public interface UserMapper{

    UserDTO toDTO(User user);

    List <UserDTO> toDTOs(Collection <User> user);
}



