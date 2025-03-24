package com.daw.daw.dto;

import org.mapstruct.Mapper;
import com.daw.daw.model.Ticket;

import org.mapstruct.Mapping;
import java.util.Collection;
import java.util.List;

/**
 * This interface defines a mapper for converting between Ticket and TicketDTO
 * objects.
 * It uses MapStruct to automatically generate the implementation at compile
 * time.
 * The mapper is configured to be used as a Spring component.
 */

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDTO toDTO(Ticket ticket);

    List<TicketDTO> toDTOs(Collection<Ticket> ticket);

    @Mapping(target = "id", ignore = true)
    Ticket toDomain(TicketDTO TicketDTO);

}
