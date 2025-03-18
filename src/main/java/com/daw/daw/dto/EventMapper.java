package com.daw.daw.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.daw.daw.model.Event;
import com.daw.daw.model.User;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.Collection;

import java.util.List;

@Mapper (componentModel = "spring")
public interface EventMapper{

    EventDTO toDTO(Event event);
    List <EventDTO> toDTOs(Collection <Event> event);

    @Mapping(target = "id", ignore = true)
    Event toDomain(EventDTO eventDTO);

}



