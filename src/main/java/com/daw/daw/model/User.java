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

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String phone;

    private String encodedPassword;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    private boolean image;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;


    public User() {
    }
    
    public User(String name, String email, String phone, String encodedPassword, List<String> roles, java.sql.Blob imageFile) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.encodedPassword = encodedPassword;
        this.roles = roles;
        this.imageFile = imageFile;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone (String telefono) {
        this.phone = telefono;
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

    public void setImageFile (Blob imageFile) {
        this.imageFile = imageFile;
    }

    public boolean getImage() {
        return image;
    }

    public void setImage (boolean image) {
        this.image = image;
    }
}
