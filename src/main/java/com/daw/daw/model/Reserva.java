package com.daw.daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity(name = "reserva_table")
public class Reserva {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    private String userName;

    private String userEmail;

    private String bussinesName;

    private int num_personas;

    private String eventDescript;

    private String estado;

    private Date event_date;



    public Reserva() {
    }

    public Reserva(String userName, String userEmail, String bussinesName, int num_personas, String eventDescript, String estado, Date event_date) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.bussinesName = bussinesName;
        this.num_personas = num_personas;
        this.eventDescript = eventDescript;
        this.estado = estado;
        this.event_date = event_date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getBussinesName() {
        return bussinesName;
    }

    public void setBussinesName(String bussinesName) {
        this.bussinesName = bussinesName;
    }

    public int getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(int num_personas) {
        this.num_personas = num_personas;
    }

    public String getEventDescript() {
        return eventDescript;
    }

    public void setEventDescript(String eventDescript) {
        this.eventDescript = eventDescript;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    

   



}
