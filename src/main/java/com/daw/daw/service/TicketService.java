package com.daw.daw.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.daw.repository.TicketRepository;
import com.daw.daw.dto.TicketDTO;
import com.daw.daw.dto.TicketMapper;
import com.daw.daw.model.Ticket;

/**
 * Service class for managing tickets.
 * This class provides methods to perform CRUD operations on Ticket entities,
 * including finding tickets by various criteria, creating, updating, and
 * deleting tickets.
 * It uses TicketRepository for database operations and TicketMapper for
 * converting
 * between Ticket and TicketDTO objects.
 * 
 * The service is annotated with @Service to indicate that it's a Spring service
 * component.
 * Dependencies are injected using @Autowired.
 */

@Service
public class TicketService {

    @Autowired
    private TicketRepository tickets;

    @Autowired
    private TicketMapper ticketMapper;

    private Ticket toDomain(TicketDTO createRequestTicketDTO) {
        return ticketMapper.toDomain(createRequestTicketDTO);
    }

    public Collection<TicketDTO> findAll() {
        return ticketMapper.toDTOs(tickets.findAll());
    }

    public TicketDTO findById(Long id) {
        return ticketMapper.toDTO(tickets.findById(id).orElseThrow());
    }

    public Collection<TicketDTO> findByCategory(String category) {
        return ticketMapper.toDTOs(tickets.findByCategory(category));
    }

    public Collection<TicketDTO> findByTitle(String title) {
        return ticketMapper.toDTOs(tickets.findByTitle(title));
    }

    public Collection<TicketDTO> findByUserOwner(String userOwner) {
        return ticketMapper.toDTOs(tickets.findByUserOwner(userOwner));
    }

    public Collection<TicketDTO> findByDni(String dni) {
        return ticketMapper.toDTOs(tickets.findByDni(dni));
    }

    public Collection<TicketDTO> findByTitleAndGender(String title, String gender) {
        return ticketMapper.toDTOs(tickets.findByTitleAndGender(title, gender));
    }

    public Collection<TicketDTO> findByUserEmail(String userEmail) {
        return ticketMapper.toDTOs(tickets.findByUserEmail(userEmail));
    }

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = toDomain(ticketDTO);
        return ticketMapper.toDTO(tickets.save(ticket));
    }

    public void deleteTicket(Long id) {
        tickets.deleteById(id);
    }

    public void deleteAllTickets() {
        tickets.deleteAll();
    }

    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = toDomain(ticketDTO);
        ticket.setId(id);
        return ticketMapper.toDTO(tickets.save(ticket));
    }
}
