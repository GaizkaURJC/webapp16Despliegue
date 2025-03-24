package com.daw.daw.controller.MVC;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.daw.daw.model.Event;
import com.daw.daw.model.User;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.repository.TicketRepository;

/**
 * This class serves as the controller for handling MVC
 * requests related to events within the web application. It is part of the
 * 'com.daw.daw.controller.MVC' package and is responsible for managing the
 * interactions between the user interface and the event-related data.
 * 
 * The controller processes incoming HTTP requests, interacts with the service
 * layer to perform business logic, and returns appropriate views to the user.
 * 
 * Note: Specific methods within this controller handle various event-related
 * operations such as creating, updating, and deleting events, as well as
 * rendering event details and lists.
 */

@Controller
@RequestMapping("/events")
public class EventMVCController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // EVENT TYPES
    // concert = concert
    // clubbing = party
    @PostMapping("/conciertoCreate")
    public String crearConcierto(@RequestParam("title") String tituloConcierto,
            @RequestParam("description") String conciertoDescription,
            @RequestParam("imageFile") MultipartFile conciertoImagen,
            @RequestParam("category") String Category) {
        try {
            Blob blobImagen = new javax.sql.rowset.serial.SerialBlob(conciertoImagen.getBytes());
            Event concierto = new Event(tituloConcierto, "concert", conciertoDescription, blobImagen, Category);
            eventRepository.save(concierto);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/admin";
    }

    @PostMapping("/partyCreate")
    public String crearFiesta(@RequestParam("title") String tituloFiesta,
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
    public String clubbingRedirection(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUserLogged = authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String);

        model.addAttribute("isUserLogged", isUserLogged);

        if (isUserLogged) {
            Object principal = authentication.getPrincipal();
            String username = "";
            User user = null;

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else if (principal instanceof User) {
                username = ((User) principal).getEmail();
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
