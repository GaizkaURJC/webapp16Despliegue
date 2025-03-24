package com.daw.daw;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.daw.daw.controller.MVC.UserMVCController;
import com.daw.daw.model.Event;
import com.daw.daw.model.User;
import com.daw.daw.model.Ticket;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.TicketRepository;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.repository.BookingRepository;
import com.daw.daw.repository.CommentRepository;

@Controller
public class PageController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private final UserMVCController userController;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookingRepository reservaRepository;

    public PageController(UserRepository userRepository, EventRepository eventRepository,
            UserMVCController userController, BookingRepository reservaRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.userController = userController;
        this.reservaRepository = reservaRepository;
    }

    @GetMapping("/")
    public String form(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUserLogged = authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String);

        model.addAttribute("isUserLogged", isUserLogged);

        List<Event> allConcerts = eventRepository.findByType("concert");
        model.addAttribute("party", eventRepository.findAllByType("party"));

        if (isUserLogged) {
            Object principal = null;
            if (authentication != null) {
                principal = authentication.getPrincipal();
            }
            String username = "";
            User user = null;

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else if (principal instanceof User) {
                username = ((User) principal).getEmail(); 
                user = ((User) principal);
            }

            List<Ticket> tickets = ticketRepository.findByUserOwner(username);
            Map<String, Long> categoryCount = tickets.stream()
                    .collect(Collectors.groupingBy(Ticket::getCategory, Collectors.counting()));

            List<Map.Entry<String, Long>> sortedPreferences = categoryCount.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .collect(Collectors.toList());

            // Reorder concerts based on user preferences
            List<Event> orderedConcerts = reorderConcerts(allConcerts, sortedPreferences);
            model.addAttribute("concerts", orderedConcerts);

            System.out.println("Usuario autenticado: " + username);
            model.addAttribute("userLogged", user);
        } else {
            model.addAttribute("concerts", allConcerts);
        }

        model.addAttribute("party", eventRepository.findAllByType("party"));
        return "index";
    }

    private List<Event> reorderConcerts(List<Event> allConcerts,
            List<Map.Entry<String, Long>> sortedPreferences) {
        List<Event> orderedConcerts = new ArrayList<>();
        Set<Long> addedEventIds = new HashSet<>();

        // Add concerts from preferred categories first
        for (Map.Entry<String, Long> preference : sortedPreferences) {
            String preferredCategory = preference.getKey();
            for (Event concert : allConcerts) {
                if (concert.getCategory().equals(preferredCategory) && addedEventIds.add(concert.getId())) {
                    orderedConcerts.add(concert);
                }
            }
        }

        // Add remaining concerts
        for (Event concert : allConcerts) {
            if (addedEventIds.add(concert.getId())) {
                orderedConcerts.add(concert);
            }
        }

        return orderedConcerts;
    }

    @GetMapping("/login")
    public String loginRedirection(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isUserLogged = authentication != null && authentication.isAuthenticated()
                && !(authentication.getPrincipal() instanceof String);

        model.addAttribute("isUserLogged", isUserLogged);

        if (isUserLogged) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            Optional<User> user = userRepository.findByName(username);
            user.ifPresent(value -> model.addAttribute("userLogged", value));
        }
        return "login";
    }

    @GetMapping("/imgEvent/{id}")
    public ResponseEntity<Object> getImage(@PathVariable Long id, Model model) throws SQLException {
        Optional<Event> op = eventRepository.findById(id);
        if (op.isPresent() && op.get().getImageFile() != null) {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .body(new InputStreamResource(op.get().getImageFile().getBinaryStream()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginaDetalleConcierto/{id}")
    public String concertDetailRedirection(@PathVariable Long id, Model model) {
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
        model.addAttribute("coments", commentRepository.getComentsByEventId(id));

        return "paginaDetalleConcierto";
    }

    @GetMapping("/admin")
    public String adminRedirection(Model model) {
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
                username = ((User) principal).getEmail();
                user = ((User) principal);
            }

            System.out.println("Usuario autenticado: " + username);
            model.addAttribute("userLogged", user);
        }

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("party", eventRepository.findAllByType("party"));
        model.addAttribute("concerts", eventRepository.findAllByType("concert"));
        model.addAttribute("reservas", reservaRepository.findAll());
        return "admin";
    }

    @GetMapping("/perfil")
    public String profileRedirection(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Autenticación: " + authentication);
        System.out.println("Principal: " + authentication.getPrincipal());
        System.out.println("¿Autenticado?: " + authentication.isAuthenticated());

        boolean isUserLogged = authentication.isAuthenticated();
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

            List<Ticket> tickets = ticketRepository.findByUserOwner(username);
            model.addAttribute("tickets", tickets);
        }

        return "paginaPerfil";
    }

}