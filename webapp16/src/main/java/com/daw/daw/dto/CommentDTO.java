package com.daw.daw.dto;

/**
 * This file defines the CommentDTO class, which is a Data Transfer Object (DTO)
 * used to encapsulate data related to comments within the web application.
 * It is part of the com.daw.daw.dto package.
 */

public record CommentDTO(

        Long id,
        String username,
        String comentario,
        int eventId,
        int rate) {
    public Long getId() {
        return id;
    }

}
