package com.daw.daw.service;

import java.net.URI;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.daw.daw.dto.EventDTO;
import com.daw.daw.dto.EventMapper;
import com.daw.daw.model.Event;
import com.daw.daw.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository events;

    @Autowired
    private EventMapper eventMapper;


    public Collection<EventDTO> findAll() {
		return eventMapper.toDTOs(events.findAll());
    }

    public EventDTO findById(Long id){
      return eventMapper.toDTO(events.findById(id).orElseThrow()); 
    }

    public Collection<EventDTO> findByType(String type) {
      return eventMapper.toDTOs(events.findByType(type)); 
    }

    public ResponseEntity<Event> createEvent(EventDTO eventDTO){
      Event event = eventMapper.toDomain(eventDTO);
      events.save(event);

      URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(event.getId()).toUri();

      return ResponseEntity.created(location).body(event); 
    }

    public EventDTO deleteEvent(Long id){
      EventDTO eventDto = eventMapper.toDTO(events.findById(id).orElseThrow()); 
      events.deleteById(id);

      return eventDto;
    }
}
