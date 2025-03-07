package com.daw.daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.daw.model.Ticket;
import com.daw.daw.repository.TicketRepository;

@Controller
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/tickets/buyTicket")
    public String createTicket(
            @RequestParam("ticketName") String nombreTicket,
            @RequestParam("dni") String DNI,
            @RequestParam("gender") String genero,
            @RequestParam("eventName") String nombreEvento,
            @RequestParam("category") String categoria,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/error";
        }

        // Crear el ticket con los datos recibidos
        System.out.println("Recibido Ticket: ");
        System.out.println("Nombre: " + nombreTicket);
        System.out.println("DNI: " + DNI);
        System.out.println("gender" + genero);
        System.out.println("Evento: " + nombreEvento);
        System.out.println("Categoria: " + categoria);
        System.out.println("Usuario logeado: " + userDetails.getUsername());

        Ticket ticket = new Ticket();
        ticket.setTicketName(nombreTicket);
        ticket.setDni(DNI);
        ticket.setGender(genero);
        ticket.setTittle(nombreEvento);
        ticket.setCategory(categoria);
        ticket.setUserEmail(userDetails.getUsername());

        // Guardar en la base de datos
        System.out.println("Voy a guardar el ticket: " + ticket);
        ticketRepository.save(ticket);
        System.out.println("Ticket guardado correctamente.");

        // Redirigir al perfil o donde quieras
        return "redirect:/";
    }
}
