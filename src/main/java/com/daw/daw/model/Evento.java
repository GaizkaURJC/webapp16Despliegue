package com.daw.daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity (name = "event_table")
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private String descripcion;

    private int capacidad_max;

    private int entradas_xvender;

    public Evento() {
    }

    public Evento(String nombre, String tipo, String descripcion, int capacidad_max, int entradas_xvender) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.capacidad_max = capacidad_max;
        this.entradas_xvender = entradas_xvender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCapacidad_max() {
        return capacidad_max;
    }

    public void setCapacidad_max(int capacidad_max) {
        this.capacidad_max = capacidad_max;
    }

    public void setEntradas_xvender(int entradas_xvender) {
        this.entradas_xvender = entradas_xvender;
    }

    public int getEntradas_xvender() {
        return entradas_xvender;
    }


}
