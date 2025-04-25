package com.daw.daw.dto;

public record EventWithImageDTO(
    Long id,
    String title,
    String type,
    String description,
    String category,
    String imageBase64
){    
}
