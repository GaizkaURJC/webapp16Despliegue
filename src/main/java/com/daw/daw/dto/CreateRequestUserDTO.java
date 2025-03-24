package com.daw.daw.dto;

import java.util.List;

/**
 * This file defines the CreateRequestUserDTO class, which is used to
 * encapsulate the data required to create a new user in the web application.
 * It is part of the Data Transfer Object (DTO) pattern, facilitating the
 * transfer of data between different layers of the application.
 */

public record CreateRequestUserDTO(
                Long id,
                String name,
                String email,
                String password,
                String phone,
                List<String> roles) {
}
