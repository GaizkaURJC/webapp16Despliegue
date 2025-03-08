package com.daw.daw.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable ("id") Long id) {
        return userRepository.getUserById(id);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
		session.setAttribute("username", null);
        return "/";
    }

    @PostMapping("create")
    public String createUser(@RequestParam("name") String nombre,
                             @RequestParam("email") String correo,
                             @RequestParam("telefono") String telf,
                             @RequestParam("password") String contrasena,
                             HttpSession session) {
        if (userRepository.findByName(nombre).isPresent()) {
            return "redirect:/register?error=user_exists";  // O vuelve al formulario con mensaje de error
        }
        User user = new User(nombre, correo, telf, passwordEncoder.encode(contrasena), Arrays.asList("USER"));
        userRepository.save(user);
        if (session.isNew()){
            session.setAttribute("username", user.getNombre());
            return "redirect:/perfil";
        }
        return "redirect:/";
    }
    
    @PostMapping("authenticate")
    public String loginUser(@RequestParam("emailLogin") String correo,
                            @RequestParam("passwordLogin") String contrasena,
                        HttpSession session) {

    User user = userRepository.findByEmail(correo)
            .orElseThrow(() -> new RuntimeException("User not found"));
        session.setAttribute("username", user.getNombre());
    if (!passwordEncoder.matches(contrasena, user.getEncodedPassword())) {
        return "redirect:/login?error=true";
    }
    if(isAdmin(session)==true){
        
        return "redirect:/admin";
    }
    else {       
    session.setAttribute("username", user.getNombre());
    return "redirect:/";
    }
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
    
    @GetMapping("userPreferences")
    public String userPreferences( HttpSession session, Model model) {

        // Recuperamos el usuario que ha inciado sesion
        String user = getLoggedUser(session);
        if (user==null) {
            return "redirect:/login";     
        }

        // Buscamos los tickets que ha comprado el usuario
        List <Ticket> tickets = ticketRepository.findByUserOwner(user);

        //Contamos cuantos hay de cada categoria
        Map<String, Long> categoryCount = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getCategory, Collectors.counting()));

        //Ordenamos las categorias por preferencia de mayor a menor

        List <Map.Entry<String, Long>> preferenciasOrdenadas = categoryCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
        
        //Buscar event futuros de esas categorias
        List <Event> recomendaciones = new ArrayList<>();
        for (Map.Entry<String, Long> preferencia: preferenciasOrdenadas){
            List<Event> eventosDeEsaCategoria = eventRepository.findByCategory(preferencia.getKey())
                    .map(Arrays::asList)
                    .orElseGet(ArrayList::new);
            recomendaciones.addAll(eventosDeEsaCategoria);
        }

        //Pasamos las recomendaciones a la vista
        model.addAttribute("recomendaciones", recomendaciones);
        return "userPreferences";
    }
}
