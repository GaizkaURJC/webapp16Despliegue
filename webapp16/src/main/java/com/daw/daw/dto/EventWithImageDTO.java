package com.daw.daw.dto;

public record EventWithImageDTO(
    Long id,
    String title,
    String type,
    String description,
    String category,
    String imageBase64
){

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImageBase64() {
        return imageBase64;
    }    
}
