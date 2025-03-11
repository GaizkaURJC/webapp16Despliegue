package com.daw.daw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 import org.springframework.ui.Model;
import com.daw.daw.model.User;
import com.daw.daw.repository.TicketRepository;
import com.daw.daw.model.Ticket;
import com.daw.daw.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.stream.Collectors;
import com.daw.daw.model.Event;
import com.daw.daw.repository.EventRepository;
@Controller
@RequestMapping("/users/")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("profileImg")
	public ResponseEntity<Object> getProfileImage(HttpSession session, Model model) throws SQLException {
		String username = (String) session.getAttribute("username");
		Optional<User> user = userRepository.findByName(username);
		if (user.isPresent() && user.get().getImageFile() != null) {
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.body(new InputStreamResource(user.get().getImageFile().getBinaryStream()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable ("id") Long id) {
        return userRepository.getUserById(id);
    }

    @GetMapping("/logout")
        public String logout(HttpServletRequest request, HttpServletResponse response) {
            SecurityContextHolder.clearContext();
            request.getSession().invalidate();
            return "redirect:/";
}


    private Blob loadImage(String path) {
        try {
            InputStream inputStream = new ClassPathResource("static/" + path).getInputStream();
            return BlobProxy.generateProxy(inputStream, inputStream.available());
        } catch (IOException e) {
            System.err.println("⚠ No se pudo cargar la imagen: " + path + ". Usando imagen por defecto.");
            return BlobProxy.generateProxy(new byte[0]);
        }
    }

    @PostMapping("create")
public String createUser(@RequestParam("name") String nombre,
                         @RequestParam("email") String correo,
                         @RequestParam("telefono") String telf,
                         @RequestParam("password") String contrasena,
                         HttpServletRequest request) {

    if (userRepository.findByName(correo).isPresent()) {
        return "redirect:/register?error=user_exists";  // O vuelve al formulario con mensaje de error
    }
    Blob defUserImg = loadImage("img/defuser.webp");
    User user = new User(nombre, correo, telf, passwordEncoder.encode(contrasena), Arrays.asList("USER"), defUserImg);
    userRepository.save(user);

    // Autenticar al usuario después de registrarlo
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String role : user.getRoles()) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
    }

    // Crear token de autenticación
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, authorities);

    // Guardar en el contexto de seguridad
    SecurityContextHolder.getContext().setAuthentication(auth);

    // Asociar el contexto de seguridad a la sesión
    request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

    return "redirect:/";  // Redirigir a la página principal
}
    
    @PostMapping("authenticate")
    public String loginUser(@RequestParam("emailLogin") String correo,
            @RequestParam("passwordLogin") String contrasena,
            HttpServletRequest request) {

        User user = userRepository.findByEmail(correo)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(contrasena, user.getEncodedPassword())) {
            return "redirect:/login?error=true";
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        // Crear token de autenticación
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, authorities);

        // Guardar en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Asociar el contexto de seguridad a la sesión
        request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin";
        }
        return "redirect:/";
    }


    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable ("id") Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/all")
    void deleteUsers(){
        userRepository.deleteAll();
    }
    
    @GetMapping("isLogged")
    public boolean isLogged(HttpSession session) {
        return session.getAttribute("username") != null;
    }

    @GetMapping("isAdmin")
    public boolean isAdmin(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if ("admin".equals(username)) {
            return true;
        }else 
        return false;
    }
    

    @GetMapping("getLoggedUser")
    public String getLoggedUser(HttpSession session) {
        return (String) session.getAttribute("username");
    }
    
    

    @PostMapping("deleteUser")
    public String deleteUseString(@RequestParam Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PutMapping("updateUser")
public String updateUser(@RequestParam Long id, 
                         @RequestParam("user") String name, 
                         @RequestParam("email") String emailUser, 
                         @RequestParam("password") String Password, 
                         HttpServletRequest request) { 
    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        user.setName(name);
        user.setEmail(emailUser);
        user.setEncodedPassword(passwordEncoder.encode(Password));
        userRepository.save(user);
        return "redirect:/admin";
    } else {
        return "redirect:/admin?error=user_not_found";
    }
}
    
}
