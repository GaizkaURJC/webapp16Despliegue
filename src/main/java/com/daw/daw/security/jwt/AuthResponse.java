package com.daw.daw.security.jwt;

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

	private Status status;
	private String message;
	private String error;

	public enum Status {
		SUCCESS, FAILURE
	}

	public AuthResponse() {
	}

	public AuthResponse(Status status, String message) {
		this.status = status;
		this.message = message;
	}

	public AuthResponse(Status status, String message, String error) {
		this.status = status;
		this.message = message;
		this.error = error;
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

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "LoginResponse [status=" + status + ", message=" + message + ", error=" + error + "]";
	}

}
