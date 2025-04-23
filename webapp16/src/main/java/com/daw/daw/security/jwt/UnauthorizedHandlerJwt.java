package com.daw.daw.security.jwt;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * This class serves as a custom implementation of the AuthenticationEntryPoint
 * interface
 * to handle unauthorized access attempts in a Spring Security context. When an
 * AuthenticationException is thrown, this handler will log the unauthorized
 * error and
 * send an HTTP 401 Unauthorized response along with a message and the requested
 * path.
 */

@Component
public class UnauthorizedHandlerJwt implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(UnauthorizedHandlerJwt.class);

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException {
    logger.info("Unauthorized error: {}", authException.getMessage());

    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
        "message: %s, path: %s".formatted(authException.getMessage(), request.getServletPath()));
  }
}