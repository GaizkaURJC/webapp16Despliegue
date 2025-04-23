package com.daw.daw.controller.API;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Collection;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.daw.daw.dto.UserDTO;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.dto.UserMapper;
import com.daw.daw.security.CSRFHandlerConfiguration;
import com.daw.daw.dto.CreateRequestUserDTO;
import com.daw.daw.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * This class is a REST controller for managing users in the application.
 * It provides endpoints for various user-related operations such as:
 * - Retrieving the current authenticated user's details.
 * - Fetching a paginated list of users.
 * - Creating a new user.
 * - Deleting all users or a specific user by ID.
 * - Replacing a user's details.
 * - Managing user images, including uploading, retrieving, replacing, and
 * deleting images.
 * 
 * The controller uses services and repositories to handle the business logic
 * and data access.
 * It also handles exceptions such as NoSuchElementException when a user is not
 * found.
 */

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {


    @Autowired
    private UserMapper userMapper;

    private final CSRFHandlerConfiguration CSRFHandlerConfiguration;

    
    private final  UserService userService;

    UserRestController(CSRFHandlerConfiguration CSRFHandlerConfiguration, UserService userService) {
        this.CSRFHandlerConfiguration = CSRFHandlerConfiguration;
        this.userService = userService;
    }

    @Operation (summary = "Get all the users")
    @GetMapping("/me")
public ResponseEntity<UserDTO> me(HttpServletRequest request) {
    Principal principal = request.getUserPrincipal();

    if (principal != null) {
        UserDTO userDTO = userService.getMe(principal.getName());
        return ResponseEntity.ok(userDTO);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
}

    @Operation(summary = "Get all users")
    @GetMapping("/")
    public Page<UserDTO> getUsers(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserDTO> userPage = userService.findAll(pageable).map(userMapper::toDTO);
        return userPage;
    }

    @Operation(summary = "Get a single user by its id")
    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @Operation(summary = "Create a new user")
    @PostMapping("/")
    public ResponseEntity<CreateRequestUserDTO> createUser(@RequestBody CreateRequestUserDTO createRequestUserDTO) {
        createRequestUserDTO = userService.createUser(createRequestUserDTO);
        URI location = fromCurrentRequest().path("/{id}")
                .buildAndExpand(createRequestUserDTO.id()).toUri();
        return ResponseEntity.created(location).body(createRequestUserDTO);
    }

    @Operation(summary = "Delete all users")
    @DeleteMapping("/")
    public Collection<UserDTO> deleteAllUsers() {
        return userService.deleteAllUsers();
    }

    @Operation(summary = "Delete a user by its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a user that already exists")
    @PutMapping("/{id}")
    public UserDTO replaceUser(@PathVariable Long id, @RequestBody CreateRequestUserDTO updateUserDTO) {
        return userService.replaceUser(id, updateUserDTO);
    }
    
    @Operation(summary = "Create a user image")
    @PostMapping("/{id}/image")
    public ResponseEntity <Object> createUserImage(@PathVariable long id, @RequestParam MultipartFile imageFile) 
        throws IOException {

        userService.createUserImage((id), imageFile.getInputStream(), imageFile.getSize());
        URI location = fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Get a user image")
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> getUserImage(@PathVariable long id) throws IOException, SQLException {

        Resource postImage = userService.getUserImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(postImage);

    }

    @Operation(summary = "Replace a user image")
    @PutMapping("{id}/image")
    public ResponseEntity<Object> replaceUserImage(@PathVariable Long id, @RequestParam MultipartFile imageFile)
            throws IOException {

        userService.replaceUserImage(id, imageFile.getInputStream(), imageFile.getSize());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a user image")
    @DeleteMapping("/{id}/image")
    public ResponseEntity<Object> deleteUserImage(@PathVariable Long id) throws IOException {

        userService.deleteUserImage(id);
        return ResponseEntity.noContent().build();
    }
}
