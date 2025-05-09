package com.daw.daw.controller.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import com.daw.daw.security.jwt.AuthResponse;
import com.daw.daw.security.jwt.JwtTokenProvider;
import com.daw.daw.security.jwt.AuthResponse.Status;
import com.daw.daw.security.jwt.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daw.daw.security.jwt.UserLoginService;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

/**
 * This class is a REST controller for handling authentication-related requests.
 * It provides endpoints for user login, token refresh, and logout.
 * 
 * - /api/auth/login: Authenticates a user based on the provided login request.
 * - /api/auth/refresh: Refreshes the authentication token using a refresh token
 * stored in a cookie.
 * - /api/auth/logout: Logs out the user and invalidates the session.
 * 
 * The controller uses the UserLoginService to perform the actual authentication
 * logic.
 * 
 * Note: The refresh token is expected to be provided as a cookie named
 * "RefreshToken".
 */

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

    @Autowired
    private UserLoginService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // Ensure the injection of the JwtTokenProvider

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateAccessToken(userDetails);
        
        // Configura cookie segura
        Cookie cookie = new Cookie("AuthToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Cambiar a true en producción con HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60); // 1 día
        response.addCookie(cookie);
        
        return ResponseEntity.ok(Map.of(
            "token", token,
            "user", userDetails
        ));
    } catch (BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }
}


    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@CookieValue(name = "RefreshToken", required = false) String refreshToken, HttpServletResponse response) {
        return userService.refresh(response, refreshToken);
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(HttpServletResponse response) {

        return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userService.logout(response)));

    }
}
