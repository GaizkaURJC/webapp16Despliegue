package com.daw.daw.dto;

import java.time.LocalDateTime;

/**
 * This file defines a record class `TicketDTO` in the package
 * `com.daw.daw.dto`.
 * The `TicketDTO` record is used to encapsulate data related to a ticket,
 * including
 * the ticket's ID, user email, user owner, DNI, ticket name, ticket date,
 * title, gender, and category.
 * This record provides a concise way to store and transfer ticket information
 * within the application.
 */

public record TicketDTO(
        Long id,
        String userEmail,
        String userOwner,
        String dni,
        String ticketName,
        LocalDateTime ticketDate,
        String title,
        String gender,
        String category) {

        public Long getId() {
                return id;
        }

        public String getGender() {
                return gender;
        }
       
}
