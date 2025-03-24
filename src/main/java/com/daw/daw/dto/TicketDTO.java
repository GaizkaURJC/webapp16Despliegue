package com.daw.daw.dto;


import org.springframework.cglib.core.Local;

public record TicketDTO (
    Long id,
    String userEmail,
    String dni,
    String ticketName,  
    String title,
    String gender,
    String category) 
    {}
