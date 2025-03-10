package com.daw.daw;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.daw.daw.controller.UserController;
import com.daw.daw.model.Coments;
import com.daw.daw.model.Event;
import com.daw.daw.model.User;
import com.daw.daw.model.Image;
import com.daw.daw.model.Ticket;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.ImageRepository;
import com.daw.daw.repository.TicketRepository;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.repository.ReservaRepository;
import com.daw.daw.repository.ComentsRepository;

@Controller
public class PageController {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private final UserController userController;

	@Autowired
	private ComentsRepository commentRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	public PageController(UserRepository userRepository, EventRepository eventRepository,
			UserController userController , ReservaRepository reservaRepository) {
		this.userRepository = userRepository;
		this.eventRepository = eventRepository;
		this.userController = userController;
		this.reservaRepository = reservaRepository;
	}

	@GetMapping("/")
	public String form(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");

		boolean isUserLogged = (username != null);
		model.addAttribute("isUserLogged", isUserLogged);

		if (isUserLogged) {
			Optional<User> user = userRepository.findByName(username);
			user.ifPresent(value -> model.addAttribute("userLogged", value));
		}

		model.addAttribute("party", eventRepository.findAllByTipo("party"));
		model.addAttribute("concerts", eventRepository.findByTipo("concert"));
		return "index";
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
	public String concertDetailRedirection(HttpSession session, @PathVariable Long id, Model model) {
		String username = (String) session.getAttribute("username");

		boolean isUserLogged = (username != null);
		model.addAttribute("isUserLogged", isUserLogged);
		if (isUserLogged) {
			Optional<User> user = userRepository.findByName(username);
			user.ifPresent(value -> model.addAttribute("userLogged", value));
		}
		model.addAttribute("event", eventRepository.findById(id).get());
		List<Coments> coments = commentRepository.getComentsByEventId(id);
		model.addAttribute("coments", coments);

		return "paginaDetalleConcierto";
	}

	@GetMapping("/admin")
	public String adminRedirection(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");

		boolean isUserLogged = (username != null);
		model.addAttribute("isUserLogged", isUserLogged);

		if (isUserLogged) {
			Optional<User> user = userRepository.findByName(username);
			user.ifPresent(value -> model.addAttribute("userLogged", value));
		}
		model.addAttribute("users", userRepository.findAll());
		model.addAttribute("party", eventRepository.findAllByTipo("party"));
		model.addAttribute("concerts", eventRepository.findByTipo("concert"));
		model.addAttribute("reservas", reservaRepository.findAll());
		return "admin";
	}

	@GetMapping("/perfil")
	public String profileRedirection(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		boolean isUserLogged = (username != null);
		model.addAttribute("isUserLogged", isUserLogged);

		if (isUserLogged) {
			Optional<User> user = userRepository.findByName(username);
			user.ifPresent(value -> model.addAttribute("userLogged", value));

			List<Ticket> tickets = ticketRepository.findByUserOwner(username);

			List<Ticket> formattedTickets = tickets.stream()
					.map(ticket -> {
						ticket.setCategory(ticket.getCategory().toUpperCase());
						return ticket;
					})
					.collect(Collectors.toList());

			model.addAttribute("tickets", formattedTickets);
		}

		model.addAttribute("username", session.getAttribute("username"));

		return "paginaPerfil";
	}

}