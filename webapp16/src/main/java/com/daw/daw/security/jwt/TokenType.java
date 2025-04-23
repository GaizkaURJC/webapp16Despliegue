package com.daw.daw.security.jwt;

import java.time.Duration;

/**
 * This enum defines the types of tokens used in the application for security
 * purposes.
 * It includes two types of tokens: ACCESS and REFRESH, each with a specific
 * duration and cookie name.
 * ACCESS tokens are used for short-lived authentication, while REFRESH tokens
 * are used to obtain new access tokens.
 */

public enum TokenType {

    ACCESS(Duration.ofMinutes(5), "AuthToken"),
    REFRESH(Duration.ofDays(7), "RefreshToken");

    // Token lifetime in seconds

    public final Duration duration;
    public final String cookieName;

    TokenType(Duration duration, String cookieName) {
        this.duration = duration;
        this.cookieName = cookieName;
    }
}
