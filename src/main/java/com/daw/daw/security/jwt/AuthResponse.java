package com.daw.daw.security.jwt;

import java.util.List;

public class AuthResponse {

    private List<String> roles;
    private String token;
    private Status status;
    private String message;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Status {
        SUCCESS, FAILURE
    }

    public AuthResponse() {
    }

    public AuthResponse(String token, List<String> roles) {
        this.token = token;
        this.roles = roles;
    }

    public AuthResponse(Status status, String message) {
        this.status = status;
        this.message = message;
    }
}