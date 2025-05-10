package com.daw.daw.service;

import java.net.URI;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daw.daw.dto.EventDTO;
import com.daw.daw.dto.EventWithImageDTO;
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

    public Page<EventWithImageDTO> findAllEventsWithImages(Pageable pageable) {
        Page<Event> eventsPage = eventRepository.findAll(pageable);
        
        return new PageImpl<>(
            eventsPage.getContent().stream()
                .map(this::convertToEventWithImageDTO)
                .collect(Collectors.toList()),
            pageable,
            eventsPage.getTotalElements()
        );
    }

    // Método para obtener imagen de un evento específico
    public byte[] getEventImage(Long id) throws SQLException {
        Event event = eventRepository.findById(id).orElseThrow();
        Blob imageBlob = event.getImageFile();
        return imageBlob.getBytes(1, (int) imageBlob.length());
    }


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

  // Método auxiliar para convertir Event a EventWithImageDTO
  private EventWithImageDTO convertToEventWithImageDTO(Event event) {
    try {
        Blob imageBlob = event.getImageFile();
        String imageBase64 = null;

        if (imageBlob != null) {
            byte[] bytes = imageBlob.getBytes(1, (int) imageBlob.length());
            imageBase64 = Base64.getEncoder().encodeToString(bytes);
        }

        return new EventWithImageDTO(
            event.getId(),
            event.getTitle(),
            event.getType(),
            event.getDescription(),
            event.getCategory(),
            imageBase64 // Puede ser null si no hay imagen
        );
    } catch (SQLException e) {
        throw new RuntimeException("Error processing image", e);
    }
}
public ResponseEntity<Event> createEventWithImage(EventWithImageDTO dto) {
    Event event = new Event();
    event.setTitle(dto.getTitle());
    event.setType(dto.getType());
    event.setDescription(dto.getDescription());
    event.setCategory(dto.getCategory());

    // Convertir base64 a BLOB
    if (dto.getImageBase64() != null && !dto.getImageBase64().isEmpty()) {
        byte[] imageBytes = Base64.getDecoder().decode(
            dto.getImageBase64().split(",")[1] // Para eliminar el encabezado: data:image/jpeg;base64,...
        );
        try {
            Blob imageBlob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
            event.setImageFile(imageBlob);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Blob from image bytes", e);
        }
    }

    eventRepository.save(event);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(event.getId()).toUri();

    return ResponseEntity.created(location).body(event);
}

}
