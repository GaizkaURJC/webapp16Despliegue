package com.daw.daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "coments_table")
public class Coments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String comentario;
    private int valoracion;
    private int eventId;

    // Constructor vacío obligatorio para JPA
    public Coments() {}

    public Coments(int valoracion, String comentario, String  username, int eventId) {
        this.username = username;
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.eventId = eventId;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public int getValoracion() { return valoracion; }
    public void setValoracion(int valoracion) { this.valoracion = valoracion; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }
}