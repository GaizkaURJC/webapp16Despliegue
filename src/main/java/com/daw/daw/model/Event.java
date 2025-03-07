package com.daw.daw.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;

@Entity(name = "event2_table")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @Lob
    private Blob imageFile;

    private String title;

    private String tipo;

    private String category;

    public Event() {

    }

    public Event(String title, String tipo, String description, java.sql.Blob imageFile, String category) {
        this.title = title;
        this.tipo = tipo;
        this.imageFile = imageFile;
        this.description = description;
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
