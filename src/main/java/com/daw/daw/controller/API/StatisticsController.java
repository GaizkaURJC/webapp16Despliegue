package com.daw.daw.controller.API;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
import java.util.HashMap;
import com.daw.daw.repository.TicketRepository;

/**
 * This class is a REST controller for managing statistics.
 * It provides endpoints to get statistics about the tickets.
 */

@RestController
@RequestMapping("/api/v1/stats")
public class StatisticsController {

    @Autowired
    private TicketRepository ticketRepository; // O el repositorio adecuado

    @GetMapping("/gender")
    public ResponseEntity<Map<String, Long>> getGenderDistribution() {
        long maleCount = ticketRepository.countByGender("Hombre");
        long femaleCount = ticketRepository.countByGender("Mujer");

        Map<String, Long> genderDistribution = new HashMap<>();
        genderDistribution.put("Hombre", maleCount);
        genderDistribution.put("Mujer", femaleCount);

        return ResponseEntity.ok(genderDistribution);
    }
}
