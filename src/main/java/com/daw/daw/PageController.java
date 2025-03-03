package com.daw.daw; 

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.repository.UserRepository;
import com.daw.daw.model.User;

@Controller 
public class PageController { 
	@GetMapping("/") 
	public String form(Model model) {
        return "index"; 
	}

	@GetMapping("/clubbing") 
	public String clubingRedirection(Model model) {
        return "clubing"; 
	}

	@GetMapping("/paginaDetalleConcierto") 
	public String concertRedirection(Model model) {
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

	
