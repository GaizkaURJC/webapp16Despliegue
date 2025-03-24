package com.daw.daw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "reserva_table")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    private String userEmail;

    private String bussinesName;

    private int capacity;

    private String eventDescript;

    private String status;

    public Booking() {
    }

    public Booking(String userName, String userEmail, String bussinesName, int capacity, String eventDescript,
            String status) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.bussinesName = bussinesName;
        this.capacity = capacity;
        this.eventDescript = eventDescript;
        this.status = status;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getEventDescript() {
        return eventDescript;
    }

    public void setEventDescript(String eventDescript) {
        this.eventDescript = eventDescript;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
