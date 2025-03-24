package com.daw.daw.dto;

public record BookingDTO(
                Long id,
                String userName,
                String userEmail,
                String bussinesName,
                int capacity,
                String eventDescript,
                String status) {
}
