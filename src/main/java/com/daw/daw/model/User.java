package com.daw.daw.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity(name= "user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String email;

    private String telefono;

    private String encodedPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    private Date fecharegistro;

    public User() {
    }
    
    public User(String name, String email, String telefono, String encodedPassword, List<String> roles) {
        this.name = name;
        this.email = email;
        this.telefono = telefono;
        this.encodedPassword = encodedPassword;
        this.roles = roles;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre (String name) {
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

    public String getEncondedPaswword() {
        return encodedPassword;
    }

    public void setEncondedPaswword (String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles (List<String> roles) {
        this.roles = roles;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro (Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

}
