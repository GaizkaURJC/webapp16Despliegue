package com.daw.daw.dto;

import java.util.List;
public record UserDTO(

    Long id,
    String name,
    String email,
    String phone,
    List <String> roles,
    boolean image) {
    
}
