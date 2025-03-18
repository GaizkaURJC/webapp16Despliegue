package com.daw.daw.dto;

import java.util.List;

public record CreateRequestUserDTO(
        Long id,
        String name,
        String email,
        String password,
        String phone,
        List <String> roles) {

}
