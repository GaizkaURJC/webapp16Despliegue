package com.daw.daw.dto;

import org.mapstruct.Mapper;
import com.daw.daw.model.User;

@Mapper (componentModel = "spring")
    public interface UserMapper{

        UserDTO toDTO(User user);
    }



