package com.daw.daw.controller.API;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import java.security.Principal;
import java.sql.SQLException;
import java.util.NoSuchElementException;
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
import org.springframework.data.domain.Pageable;

import com.daw.daw.dto.UserDTO;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.dto.UserMapper;
import com.daw.daw.security.CSRFHandlerConfiguration;
import com.daw.daw.dto.CreateRequestUserDTO;
import com.daw.daw.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    private final CSRFHandlerConfiguration CSRFHandlerConfiguration;

    @Autowired
    private UserService userService;

    UserRestController(CSRFHandlerConfiguration CSRFHandlerConfiguration, UserRepository userRepository) {
        this.CSRFHandlerConfiguration = CSRFHandlerConfiguration;
        this.userRepository = userRepository;
    }

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
	public Page <UserDTO> getUsers(Pageable pageable) {

        return userRepository.findAll(pageable).map(userMapper::toDTO);
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
        return userService.replaceUser(id, updateUserDTO);
    }
    
    @PostMapping("/{id}/image")
    public ResponseEntity <Object> createUserImage(@PathVariable long id, @RequestParam MultipartFile imageFile) 
        throws IOException {

            userService.createUserImage((id),imageFile.getInputStream(),imageFile.getSize());
            URI location = fromCurrentRequest().build().toUri();
            return ResponseEntity.created(location).build();
        }

    @GetMapping("/{id}/image")
    public ResponseEntity <Object> getUserImage (@PathVariable long id) throws IOException, SQLException {

        Resource postImage= userService.getUserImage(id);


        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE,"image/jpeg")
            .body(postImage);
        
    }

    @PutMapping("{id}/image")
    public ResponseEntity <Object> replaceUserImage(@PathVariable Long id, @RequestBody MultipartFile imageFile) throws IOException {

        userService.replaceUserImage(id, imageFile.getInputStream(), imageFile.getSize());        
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity <Object> deleteUserImage(@PathVariable Long id) throws IOException {

        userService.deleteUserImage(id);
        return ResponseEntity.noContent().build();
    }
}  
