package com.daw.daw; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller 
public class controller { 
	
	@GetMapping("/") 
	public String form(Model model) {
        return "index"; 
	}
	
}