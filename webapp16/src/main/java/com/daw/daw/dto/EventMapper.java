package com.daw.daw.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.daw.daw.model.Event;

import java.util.Collection;

import java.util.List;

/**
 * This interface defines a mapper for converting between Event and EventDTO
 * objects.
 * It uses MapStruct to automatically generate the implementation at compile
 * time.
 * The mapper is configured to be used as a Spring component.
 */

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO toDTO(Event event);

    List<EventDTO> toDTOs(Collection<Event> event);

    @Mapping(target = "id", ignore = true)
    Event toDomain(EventDTO eventDTO);

}
