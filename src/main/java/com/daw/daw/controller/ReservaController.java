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
import com.daw.daw.model.Reserva;
import com.daw.daw.repository.ComentsRepository;
import com.daw.daw.repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import java.util.Date;;


@Controller
@RequestMapping("/reserva/")
public class ReservaController {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("request")
    public String requestEvent (@RequestParam ("userName") String name,
                                @RequestParam ("userEmail") String email,
                                @RequestParam ("bussinesName") String bussinesname,
                                @RequestParam ("num_personas") int pax,
                                @RequestParam ("date") Date eventDate,
                                @RequestParam ("eventDescript") String description) {
    
        Reserva reserva = new Reserva(name, email, bussinesname, pax, description, "pendiente", eventDate);
        reservaRepository.save(reserva);
        return "redirect:/";
}
}