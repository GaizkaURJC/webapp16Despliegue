package com.daw.daw.controller.API;

import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.dto.TicketDTO;
import com.daw.daw.dto.UserDTO;
import com.daw.daw.service.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.Collection;
import java.util.NoSuchElementException;

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
    
    @Operation(summary = "Get all the tickets")

    @GetMapping("/")
    public Collection<TicketDTO> getAllTickets() {
        return ticketService.findAll();
    }

    @Operation(summary = "Get a single ticket by its id")
    @GetMapping("/{id}")
    public TicketDTO getTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }
    
    @Operation(summary = "Get all the tickets of a certain category")

    @GetMapping("/category/{category}")
    public Collection<TicketDTO> getTicketsByCategory(@PathVariable String category) {
        return ticketService.findByCategory(category);
    }

    @Operation(summary = "Get all the tickets of a certain title")
    @GetMapping("/title/{title}")
    public Collection<TicketDTO> getTicketsByTitle(@RequestParam String title) {
        return ticketService.findByTitle(title);
    }

    @Operation(summary = "Get all the tickets of a certain userOwner")
    @GetMapping("/userOwner/{userOwner}")
    public Collection<TicketDTO> getTicketsByUserOwner(@RequestParam String userOwner) {
        return ticketService.findByUserOwner(userOwner);
    }

    @Operation(summary = "Get all the tickets of a certain user by dni")
    @GetMapping("/dni/{dni}")
    public Collection<TicketDTO> getTicketsByDni(@RequestParam String dni) {
        return ticketService.findByDni(dni);
    }

    @Operation(summary = "Get all the tickets of a certain gender")
    @GetMapping("/gender/{gender}/{title}")
    public Collection<TicketDTO> getTicketsByTitleAndGender(@PathVariable String gender, @PathVariable String title) {
        return ticketService.findByTitleAndGender(title, gender);
    }

    @Operation(summary = "Update a ticket that already exists")
    @PutMapping("/{id}")
    public TicketDTO updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    @Operation(summary = "Delete a ticket by its id")
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    @Operation(summary = "Delete all tickets")
    @DeleteMapping("/")
    public void deleteAllTickets() {
        ticketService.deleteAllTickets();
    }

    @Operation(summary = "Create a new ticket")
    @PostMapping("/")
    public TicketDTO createTicket(@RequestBody TicketDTO ticket) {
        return ticketService.createTicket(ticket);
    }

    @Operation(summary = "Get all tickets owned by user")
    @GetMapping("/getMyTickets")
    public Collection<TicketDTO> getMyTickets(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            return ticketService.findByUserOwner(principal.getName());
        } else {
            throw new NoSuchElementException("usuario anonimo");
        }
    }

    @Operation(summary = "Deleta a ticket owned by user")
    @DeleteMapping("/deleteMyTicket/{id}")
    public void deleteMyTicket(@PathVariable Long id, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null && ticketService.findById(id).userOwner().equals(principal.getName())) {
            ticketService.deleteTicket(id);
        } else {
            throw new NoSuchElementException("usuario anonimo");
        }
    }
}
