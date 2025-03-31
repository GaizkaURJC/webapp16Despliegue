package com.daw.daw.service;

import java.net.URI;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daw.daw.dto.EventDTO;
import com.daw.daw.dto.EventMapper;
import com.daw.daw.model.Event;
import com.daw.daw.model.User;
import com.daw.daw.repository.EventRepository;

/**
 * EventService.java
 *
 * This file is part of the web application project for the "Desarrollo de
 * aplicaciones web" course.
 * It contains the EventService class, which provides various services related
 * to event management.
 * These services include creating, updating, deleting, and retrieving events.
 * The class interacts with the data layer to perform CRUD operations on event
 * entities.
 */

@Service
public class EventService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventRepository events;

  @Autowired
  private EventMapper eventMapper;

  public Collection<EventDTO> findAll() {
    return eventMapper.toDTOs(events.findAll());
  }

  public Page<Event> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

  public EventDTO findById(Long id) {
    return eventMapper.toDTO(events.findById(id).orElseThrow());
  }

  public Collection<EventDTO> findByType(String type) {
    return eventMapper.toDTOs(events.findByType(type));
  }

  public ResponseEntity<Event> createEvent(EventDTO eventDTO) {
    Event event = eventMapper.toDomain(eventDTO);
    events.save(event);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(event.getId()).toUri();

    return ResponseEntity.created(location).body(event);
  }

  public EventDTO deleteEvent(Long id) {
    EventDTO eventDto = eventMapper.toDTO(events.findById(id).orElseThrow());
    events.deleteById(id);

    return eventDto;
  }

  public EventDTO replaceEvent(Long id, Event updatedEvent) {
    if (events.existsById(id)) {
      updatedEvent.setId(id);
      events.save(updatedEvent);
      return (eventMapper.toDTO(updatedEvent));
    } else {
      throw new NoSuchElementException();
    }

  }
}
