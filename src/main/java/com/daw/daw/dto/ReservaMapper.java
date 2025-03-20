package com.daw.daw.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.daw.daw.model.Reserva;
import java.util.List;
import java.util.Collection;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaDTO toDTO(Reserva reserva);

    List<ReservaDTO> toDTOs(Collection<Reserva> reservas);

    @Mapping(target = "id", ignore = true)
    Reserva toDomain(ReservaDTO reservaDTO);
}
