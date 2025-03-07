package com.daw.daw;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

import com.daw.daw.model.Event;
import com.daw.daw.model.Image;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.ImageRepository;

@Controller
public class PageController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ImageRepository imageRepository;

	@GetMapping("/")
	public String form(Model model) {
		List<Event> party = eventRepository.findAllByTipo("party");
		model.addAttribute("party", party);
		List<Event> concerts = eventRepository.findByTipo("concert");
		model.addAttribute("concerts", concerts);
		return "index";
	}

	@GetMapping("/img/{id}")
	public ResponseEntity<Object> getImage(@PathVariable Long id, Model model) throws SQLException {
		Optional<Event> op = eventRepository.findById(id);
		if (op.isPresent() && op.get().getImageFile() != null) {
			Blob image = op.get().getImageFile();
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.body(new InputStreamResource(op.get().getImageFile().getBinaryStream()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	

	@GetMapping("/clubbing")
	public String clubingRedirection(Model model) {
		return "clubing";
	}

	@GetMapping("/paginaDetalleConcierto/{id}")
	public String concertDetailRedirection(@PathVariable Long id, Model model) {
		model.addAttribute("event", eventRepository.findById(id).get());
		return "paginaDetalleConcierto";
	}

	@GetMapping("/admin")
	public String adminRedirection(Model model) {
		return "admin";
	}

	@GetMapping("/perfil")
	public String profileRedirection(Model model) {
		return "paginaPerfil";
	}

}