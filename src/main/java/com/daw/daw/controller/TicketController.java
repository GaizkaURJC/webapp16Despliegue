package com.daw.daw.controller;

import java.net.URI;
import java.util.Arrays;
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
 
import com.daw.daw.model.Ticket;
import com.daw.daw.repository.TicketRepository;
import jakarta.servlet.http.HttpSession;
import com.daw.daw.model.User;
import com.daw.daw.repository.UserRepository;


@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    User user = new User();
    UserController userController = new UserController();

    @PostMapping("/buyTicket")
    public String buyTicket(HttpSession session) {
        if (userController.isLogged(session) == false) {
            return "redirect:/error";
            
        }else{
            String UserName = userController.getLoggedUser(session);
            Ticket ticket = new Ticket(UserName, 10, UserName, "Concierto de prueba","rock");
            ticketRepository.save(ticket);
            return "redirect:/perfil";
        }
    }
    

}
