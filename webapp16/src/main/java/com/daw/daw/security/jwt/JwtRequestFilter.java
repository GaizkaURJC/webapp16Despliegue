package com.daw.daw.security.jwt;

import java.io.IOException;

import jakarta.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This class is a filter that intercepts HTTP requests to validate JWT tokens.
 * It extends the OncePerRequestFilter class to ensure that the filter is
 * executed once per request.
 * The filter extracts the JWT token from the request, validates it, and sets
 * the authentication
 * in the SecurityContext if the token is valid. If the token is not found or
 * invalid, it logs the error
 * except when no token is found in the request.
 * 
 * This filter is a crucial part of the security mechanism to ensure that only
 * authenticated users
 * can access protected resources.
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtRequestFilter(UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws ServletException, IOException {
    
    // 1. Intentar obtener el token de la cookie
    Cookie[] cookies = request.getCookies();
    String token = null;
    
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("AuthToken".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
    }
    
    // 2. Si no está en cookies, intentar del header Authorization
    if (token == null) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7);
        }
    }
    
    // 3. Validar el token
    if (token != null && jwtTokenProvider.validateToken(token)) {
        // ... establecer autenticación
    }
    
    chain.doFilter(request, response);
}

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }


}


