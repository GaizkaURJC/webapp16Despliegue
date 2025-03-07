package com.daw.daw.model;


import java.util.List;
import java.util.Locale.Category;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Entity(name = "ticket_table")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String evento_Id;

    private int precio;

    private String userOwner;

    private String titulo;

    private String category; 
    

    //Añadiremos fecha de compra mas adelante

    public Ticket() {
    }

    public Ticket(String evento_Id, int precio, String userOwner, String titulo, String category) {
        this.evento_Id = evento_Id;
        this.precio = precio;
        this.userOwner = userOwner;
        this.titulo = titulo;
        this.category = category;
    }

    public String getEvento_Id() {
        return evento_Id;
    }
    
    public void setEvento_Id(String evento_Id) {
        this.evento_Id = evento_Id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(String userOwner) {
        this.userOwner = userOwner;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    
}
