package com.daw.daw.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

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
import org.springframework.web.multipart.MultipartFile;

import com.daw.daw.model.Evento;
import com.daw.daw.model.Event;
import com.daw.daw.repository.ComentsRepository;
import com.daw.daw.repository.EventoRepository;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/evento/")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventRepository eventRepository;

    @PostMapping("/evento/create")
    public String createEvent(@RequestParam("title") String title,
            @RequestParam("tipo") String tipo,
            @RequestParam("description") String description,
            @RequestParam("imageFile") MultipartFile imageFile) {

        try {
            byte[] imageBytes = imageFile.getBytes();
            Blob imageBlob = new SerialBlob(imageBytes);

            // Crear evento
            Event evento = new Event(title, tipo, description, imageBlob);
            eventRepository.save(evento);

            return "redirect:/admin";

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return "redirect:/admin"; // En caso de fallo
        }
    }
}
