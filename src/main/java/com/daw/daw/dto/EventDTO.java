package com.daw.daw.dto;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record EventDTO(

    Long id,
    String title,
    String type,
    String description,
    String category) {
    
}
