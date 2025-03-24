package com.daw.daw.controller.API;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Collection;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.daw.dto.EventDTO;
import com.daw.daw.model.Event;
import com.daw.daw.service.EventService;

@RestController
@RequestMapping("/api/v1/events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public Collection<EventDTO> getAllEvents() {
        return (eventService.findAll());
    }

    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @GetMapping("/type/{type}")
    public Collection<EventDTO> getAllEventsByType(@PathVariable String type) {
        return eventService.findByType(type);
    }

    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO event) {
        return eventService.createEvent(event);
    }

    @DeleteMapping("/{id}")
    public EventDTO deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    @PutMapping("/{id}")
    public EventDTO replaceEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventService.replaceEvent(id, updatedEvent);

    }
}
