package com.daw.daw.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record UserDTO(

    Long id,
    String name,
    String email,
    String telefono,
    List <String> roles,
    List <String> tickets,
    MultipartFile imageField) {
    
}
