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
import com.daw.daw.model.Evento;
import com.daw.daw.repository.ComentsRepository;
import com.daw.daw.repository.EventoRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/evento/")
public class EventoController {
    
    @Autowired 
    private EventoRepository eventoRepository;
}
