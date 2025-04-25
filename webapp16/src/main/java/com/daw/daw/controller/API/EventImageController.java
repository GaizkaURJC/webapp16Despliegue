package com.daw.daw.controller.API;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.dto.EventWithImageDTO;
import com.daw.daw.service.EventService;

@RestController
@RequestMapping("/api/v1/events")
public class EventImageController {

    @Autowired
    private EventService eventService;

    // Endpoint para obtener eventos con imágenes (base64)
    @GetMapping("/with-images")
    public Page<EventWithImageDTO> getAllEventsWithImages(Pageable pageable) {
        return eventService.findAllEventsWithImages(pageable);
    }

    // Endpoint para obtener imagen binaria
    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getEventImage(@PathVariable Long id) {
        try {
            byte[] imageBytes = eventService.getEventImage(id);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Ajusta según tu tipo de imagen
            
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (SQLException e) {
            return ResponseEntity.notFound().build();
        }
    }
}