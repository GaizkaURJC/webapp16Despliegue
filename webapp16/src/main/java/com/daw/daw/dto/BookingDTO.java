package com.daw.daw.dto;

/**
 * This file defines the BookingDTO class, which is a Data Transfer Object (DTO)
 * used to encapsulate booking-related data for transfer between different
 * layers
 * of the web application. The class is part of the com.daw.daw.dto package.
 */

public record BookingDTO(
        Long id,
        String userName,
        String userEmail,
        String bussinesName,
        int capacity,
        String eventDescript,
        String status) {
}
