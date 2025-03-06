package com.daw.daw.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;
import java.sql.Blob;
 
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
 
import com.daw.daw.model.Event;
import com.daw.daw.repository.EventRepository;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//TIPOS DE EVENTO
// conciertos = concert
// clubbing = party
    @PostMapping("/conciertoCreate")
    public String crearConcierto(@RequestParam("title") String tituloConcierto,
                                 @RequestParam("description") String conciertoDescription,
                                 @RequestParam("imageFile") MultipartFile conciertoImagen) {
        try {
            Blob blobImagen = new javax.sql.rowset.serial.SerialBlob(conciertoImagen.getBytes());
            Event concierto = new Event(tituloConcierto, "concert", conciertoDescription, blobImagen);
            eventRepository.save(concierto);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/";
    }

    @PostMapping("/partyCreate")
    public String crearFiesta (@RequestParam("title") String tituloFiesta,
                               @RequestParam("partyDetails") String fiestaDescription,
                               @RequestParam("partyImageFile") MultipartFile fiestaImagen) {
        try {
            Blob blobImagen = new javax.sql.rowset.serial.SerialBlob(fiestaImagen.getBytes());
            Event fiesta = new Event(tituloFiesta, "party", fiestaDescription, blobImagen);
            eventRepository.save(fiesta);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/";
    }
}

