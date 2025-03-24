package com.daw.daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * This file defines the Comment class within the com.daw.daw.model package.
 * It is part of a web application project for the 2024-2025 academic year.
 * The Comment class is likely used to represent user comments in the
 * application,
 * encapsulating properties and behaviors related to comments.
 */

@Entity(name = "coments_table")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String username;
    private String comentario;
    private int rate;
    private int eventId;

    // Default constructor required for JPA
    public Comment() {
    }

    public Comment(int rate, String comentario, String username, int eventId) {
        this.username = username;
        this.rate = rate;
        this.comentario = comentario;
        this.eventId = eventId;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}