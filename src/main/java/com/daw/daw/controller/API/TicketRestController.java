package com.daw.daw.controller.API;

import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.dto.TicketDTO;
import com.daw.daw.service.TicketService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * This class is a REST controller for managing tickets.
 * It provides endpoints to create, read, update, and delete tickets.
 * It also provides endpoints to filter tickets by various criteria.
 */

@RestController
@RequestMapping("/api/v1/tickets")

public class TicketRestController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public Collection<TicketDTO> getAllTickets() {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public TicketDTO getTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @GetMapping("/category/{category}")
    public Collection<TicketDTO> getTicketsByCategory(@PathVariable String category) {
        return ticketService.findByCategory(category);
    }

    @GetMapping("/title/{title}")
    public Collection<TicketDTO> getTicketsByTitle(@RequestParam String title) {
        return ticketService.findByTitle(title);
    }

    @GetMapping("/userOwner/{userOwner}")
    public Collection<TicketDTO> getTicketsByUserOwner(@RequestParam String userOwner) {
        return ticketService.findByUserOwner(userOwner);
    }

    @GetMapping("/dni/{dni}")
    public Collection<TicketDTO> getTicketsByDni(@RequestParam String dni) {
        return ticketService.findByDni(dni);
    }

    @GetMapping("/gender/{gender}/{title}")
    public Collection<TicketDTO> getTicketsByTitleAndGender(@PathVariable String gender, @PathVariable String title) {
        return ticketService.findByTitleAndGender(title, gender);
    }

    @PutMapping("/{id}")
    public TicketDTO updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    @DeleteMapping("/")
    public void deleteAllTickets() {
        ticketService.deleteAllTickets();
    }

    @PostMapping("/")
    public TicketDTO createTicket(@RequestBody TicketDTO ticket) {
        return ticketService.createTicket(ticket);
    }
}
