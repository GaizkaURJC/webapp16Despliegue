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

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/events")
public class EventRestController {

    @Autowired
    private EventService eventService;

    @Operation (summary = "Get all the events")
    @GetMapping("/")
    public Collection<EventDTO> getAllEvents() {
        return (eventService.findAll());
    }

    @Operation(summary = "Get a single event by its id")
    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @Operation(summary = "Get all the events of a certain type")
    @GetMapping("/type/{type}")
    public Collection<EventDTO> getAllEventsByType(@PathVariable String type) {
        return eventService.findByType(type);
    }

    @Operation(summary = "Create a new event")
    @PostMapping("/")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO event) {
        return eventService.createEvent(event);
    }

    @Operation(summary = "Delete an event by its id")
    @DeleteMapping("/{id}")
    public EventDTO deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }

    @Operation(summary = "Update an event that already exists")
    @PutMapping("/{id}")
    public EventDTO replaceEvent(@PathVariable Long id, @RequestBody Event updatedEvent) {
        return eventService.replaceEvent(id, updatedEvent);

    }
}
