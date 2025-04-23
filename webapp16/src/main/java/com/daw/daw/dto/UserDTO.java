package com.daw.daw.dto;

import java.util.List;

/**
 * UserDTO is a data transfer object that encapsulates user information.
 * It includes fields for user ID, name, email, phone number, roles, and a flag
 * indicating if an image is associated with the user.
 */

public record UserDTO(

        Long id,
        String name,
        String email,
        String phone,
        List<String> roles,
        boolean image) {
}
