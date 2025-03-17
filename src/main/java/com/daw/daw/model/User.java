package com.daw.daw.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.sql.Blob;
import java.util.Date;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
@Entity(name= "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;

    private String telefono;

    private String encodedPassword;

    @Lob
    private Blob imageFile;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


    public User() {
    }
    
    public User(String name, String email, String telefono, String encodedPassword, List<String> roles, java.sql.Blob imageFile) {
        this.name = name;
        this.email = email;
        this.telefono = telefono;
        this.encodedPassword = encodedPassword;
        this.roles = roles;
        this.imageFile = imageFile;
    }

    public String getNombre() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono (String telefono) {
        this.telefono = telefono;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword (String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles (List<String> roles) {
        this.roles = roles;
    }

    public Blob getImageFile() {
        return imageFile;
    }

}
