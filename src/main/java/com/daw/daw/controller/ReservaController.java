package com.daw.daw.controller;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;
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
import com.daw.daw.model.Reserva;
import com.daw.daw.repository.ComentsRepository;
import com.daw.daw.repository.ReservaRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.text.ParseException;
import java.io.IOException;
import com.daw.daw.service.PdfService;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/reserva/")
public class ReservaController {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private PdfService pdfService;

    @PostMapping("request")
    public void requestEvent(
            @RequestParam("userName") String name,
            @RequestParam("userEmail") String email,
            @RequestParam("bussinesName") String bussinesname,
            @RequestParam("num_personas") int pax,
            @RequestParam("eventDescript") String description,
            HttpServletResponse response) throws IOException, DocumentException {

        Reserva reserva = new Reserva(name, email, bussinesname, pax, description, "pendiente");
        reservaRepository.save(reserva);

        // Generar el PDF
        byte[] pdfBytes = pdfService.generarPdfReserva(reserva);

        // Configurar la respuesta HTTP para que sea una descarga
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reserva_" + reserva.getUserName() + ".pdf");
        response.getOutputStream().write(pdfBytes);
        response.flushBuffer();
    }

    @PostMapping("aceptar")
    public String aceptarReserva(@RequestParam Long id) {
        Optional<Reserva> reserva = reservaRepository.getReservaById(id);
        if (reserva.isPresent()) {
            reserva.get().setEstado("aceptada");
            reservaRepository.save(reserva.get());
            return "redirect:/admin";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("rechazar")
    public String rechazarReserva(@RequestParam Long id) {
        Optional<Reserva> reserva = reservaRepository.getReservaById(id);
        if (reserva.isPresent()) {
            reserva.get().setEstado("rechazada");
            reservaRepository.save(reserva.get());
            return "redirect:/admin";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("deleteReserva")
    public String deleteReserva(@RequestParam Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/admin";
        
    }
    
    
}
