package com.daw.daw.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Collection;
import com.daw.daw.dto.ReservaDTO;
import com.daw.daw.service.ReservaService;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaRestController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/")
    public Collection<ReservaDTO> getAllReservas() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ReservaDTO getReserva(@PathVariable Long id) {
        return reservaService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.ok(reservaService.createReserva(reservaDTO));
    }

    @PutMapping("/{id}")
    public ReservaDTO updateReserva(@PathVariable Long id, @RequestBody ReservaDTO reservaDTO) {
        return reservaService.updateReserva(id, reservaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
    }

}
