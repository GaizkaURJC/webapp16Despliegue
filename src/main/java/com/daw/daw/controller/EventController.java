package com.daw.daw.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
 
import com.daw.daw.model.Event;
import com.daw.daw.model.User;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.repository.TicketRepository;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;
    
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
                                 @RequestParam("imageFile") MultipartFile conciertoImagen,
                                 @RequestParam("category") String category) {
        try {
            Blob blobImagen = new javax.sql.rowset.serial.SerialBlob(conciertoImagen.getBytes());
            Event concierto = new Event(tituloConcierto, "concert", conciertoDescription, blobImagen, category);
            eventRepository.save(concierto);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/admin";
    }

    @PostMapping("/partyCreate")
    public String crearFiesta (@RequestParam("title") String tituloFiesta,
                               @RequestParam("partyDetails") String fiestaDescription,
                               @RequestParam("partyImageFile") MultipartFile fiestaImagen) {
        try {
            Blob blobImagen = new javax.sql.rowset.serial.SerialBlob(fiestaImagen.getBytes());
            Event fiesta = new Event(tituloFiesta, "party", fiestaDescription, blobImagen, "party");
            eventRepository.save(fiesta);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
	public String clubbingRedirection(HttpSession session, @PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUserLogged = authentication.isAuthenticated();

        model.addAttribute("isUserLogged", isUserLogged);

		if (isUserLogged) {
			Object principal = authentication.getPrincipal();
            String username = "";
            User user = null;

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else if (principal instanceof User) {
                username = ((User) principal).getEmail(); // Usa email si es lo que almacenas en User
                user = ((User) principal);
            }

            

            System.out.println("Usuario autenticado: " + username);
            model.addAttribute("userLogged", user);
		}
		model.addAttribute("event", eventRepository.findById(id).get());
        String[] partes = eventRepository.findById(id).get().getDescription().split("\\|");
        model.addAttribute("descLinea1", partes[0]);
        model.addAttribute("descLinea2", partes.length > 1 ? partes[1] : "");

        String title = eventRepository.findById(id).get().getTitle();
        int maleCount = ticketRepository.findByTitleAndGender(title, "Hombre").size();
        int femaleCount = ticketRepository.findByTitleAndGender(title, "Mujer").size();
        model.addAttribute("maleCount", maleCount);
        model.addAttribute("femaleCount", femaleCount);
		return "clubing";
	}

    @PostMapping("/deleteEvent")
    public String deleteEvent(@RequestParam Long id) {
        eventRepository.deleteById(id);
        return "redirect:/admin";
    }
}

