package com.daw.daw.security.jwt;

import java.util.List;


/**
 * This class represents an authentication response used in the web application.
 * It contains the status of the authentication process, a message, and an
 * optional error message.
 * The status can be either SUCCESS or FAILURE, indicating the result of the
 * authentication attempt.
 * This class provides constructors to create an AuthResponse with different
 * combinations of status, message, and error.
 */

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