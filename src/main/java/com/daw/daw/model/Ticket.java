package com.daw.daw.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This class represents a Ticket entity in the application.
 * It is annotated with @Entity to indicate that it is a JPA entity.
 * The class contains fields such as id, userEmail, userOwner, dni, ticketName,
 * title, gender, category, and ticketDate.
 * It uses @JsonFormat to format the ticketDate field.
 * The class is also annotated with @Component and @SessionScope to indicate
 * that it is a Spring component with session scope.
 * It includes a default constructor and a parameterized constructor for
 * creating instances of Ticket.
 */

@Component
@SessionScope
@Entity(name = "ticket_table")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userEmail;

    private String userOwner;

    private String dni;

    private String ticketName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ticketDate;

    private String title;

    private String gender;

    private String category;

    public Ticket() {
    }

    public Ticket(String userEmail, String dni, String ticketName, String title, String gender,
            String userOwner, String category, LocalDateTime ticketDate) {
        this.userEmail = userEmail;
        this.dni = dni;
        this.ticketName = ticketName;
        this.title = title;
        this.gender = gender;
        this.userOwner = userOwner;
        this.category = category;
        this.ticketDate = ticketDate;
    }

    public LocalDateTime getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDateTime ticketDate) {
        this.ticketDate = ticketDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(String userOwner) {
        this.userOwner = userOwner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
