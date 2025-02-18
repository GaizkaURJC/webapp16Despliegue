package com.daw.daw; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



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