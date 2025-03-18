package com.daw.daw.controller.API;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Collection;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.dto.UserDTO;
import com.daw.daw.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDTO me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if(principal != null) {
            return userService.getUser(principal.getName());
        } else {
            throw new NoSuchElementException("usuario anonimo");
        }
    }


    @GetMapping("/")
	public Collection <UserDTO> getBooks() {

        return userService.getAllUsers();
	}



}
