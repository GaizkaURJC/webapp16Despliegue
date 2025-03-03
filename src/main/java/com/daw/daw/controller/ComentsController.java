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
import com.daw.daw.model.Coments;
import com.daw.daw.repository.ComentsRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/coments/")
public class ComentsController {

    @Autowired
    private ComentsRepository comentsRepository;


    @Autowired
    private UserController userController;

    @PostMapping("create")
    public String addComent(@RequestParam ("rate") int valoracion, @RequestParam ("coment") String comentario, String userName, HttpSession session) {
        if (userController.isLogged(session) == false) {
            return "redirect:/error";
        }
        String UserName = userController.getLoggedUser(session);
        Coments coments = new Coments(valoracion, comentario, UserName);
        comentsRepository.save(coments);
    return "redirect:/";
    }
    
    
}