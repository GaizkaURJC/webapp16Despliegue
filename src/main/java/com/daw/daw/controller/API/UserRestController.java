package com.daw.daw.controller.API;

import java.net.URI;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Collection;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.dto.UserDTO;
import com.daw.daw.dto.CreateRequestUserDTO;
import com.daw.daw.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserDTO me(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            return userService.getMe(principal.getName());
        } else {
            throw new NoSuchElementException("usuario anonimo");
        }
    }

    @GetMapping("/")
    public Collection<UserDTO> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/")
    public ResponseEntity<CreateRequestUserDTO> createUser(@RequestBody CreateRequestUserDTO createRequestUserDTO) {
        createRequestUserDTO = userService.createUser(createRequestUserDTO);
        URI location = fromCurrentRequest().path("/{id}")
                .buildAndExpand(createRequestUserDTO.id()).toUri();
        return ResponseEntity.created(location).body(createRequestUserDTO);
    }

    @DeleteMapping("/")
    public Collection<UserDTO> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public UserDTO replaceUser(@PathVariable Long id, @RequestBody CreateRequestUserDTO updateUserDTO) {
        // TODO: process PUT request
        return userService.replaceUser(id, updateUserDTO);
    }

}
