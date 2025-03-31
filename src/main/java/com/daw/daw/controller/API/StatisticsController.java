package com.daw.daw.controller.API;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.HashMap;

import com.daw.daw.dto.TicketDTO;
import com.daw.daw.service.TicketService;

/**
 * This class is a REST controller for managing statistics.
 * It provides endpoints to get statistics about the tickets.
 */

@RestController
@RequestMapping("/api/v1/stats")
public class StatisticsController {

    @Autowired
    private TicketService ticketService;

    

    @GetMapping("/gender/{id}")
public ResponseEntity<Map<String, Long>> getGenderDistribution(@PathVariable Long id) {
    // Obtén el ticket por ID
    TicketDTO ticket = ticketService.findById(id);

    // Filtra los tickets por género
    long maleCount = ticketService.findAll().stream()
            .filter(t -> ticket.getGender().equalsIgnoreCase("Hombre") && t.getId().equals(id))
            .count();

    long femaleCount = ticketService.findAll().stream()
            .filter(t -> ticket.getGender().equalsIgnoreCase("Mujer") && t.getId().equals(id))
            .count();

    // Construye la respuesta
    Map<String, Long> genderDistribution = new HashMap<>();
    genderDistribution.put("Hombre", maleCount);
    genderDistribution.put("Mujer", femaleCount);

    return ResponseEntity.ok(genderDistribution);
}
}
