package com.daw.daw.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.daw.daw.security.jwt.AuthResponse;
import com.daw.daw.security.jwt.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.daw.daw.security.jwt.UserLoginService;
import com.daw.daw.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity <AuthResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){

        return userService.login(response, loginRequest);

    }


    
}
