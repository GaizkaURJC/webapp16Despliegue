package com.daw.daw.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.daw.daw.security.jwt.AuthResponse;
import com.daw.daw.security.jwt.AuthResponse.Status;
import com.daw.daw.security.jwt.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daw.daw.security.jwt.UserLoginService;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

    @Autowired
    private UserLoginService userService;

    @PostMapping("/login")
    public ResponseEntity <AuthResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){

        return userService.login(response, loginRequest);

    }

    @PostMapping("/refresh")
    public ResponseEntity <AuthResponse> refreshToken(@CookieValue (name="RefreshToken", required = false) String refreshToken, HttpServletResponse response){

        return userService.refresh(response, refreshToken);

    }

    @PostMapping("/logout")
    public ResponseEntity <AuthResponse> logout(HttpServletResponse response){

        return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userService.logout(response)));

    }
}
