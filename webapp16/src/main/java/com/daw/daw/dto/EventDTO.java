package com.daw.daw.dto;

/**
 * This file defines the EventDTO class within the com.daw.daw.dto package.
 * The EventDTO class is used as a Data Transfer Object (DTO) to encapsulate
 * and transfer event-related data between different layers of the application.
 */

public record EventDTO(
        Long id,
        String title,
        String type,
        String description,
        String category) {
}
