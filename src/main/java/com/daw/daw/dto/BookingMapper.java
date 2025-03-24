package com.daw.daw.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.daw.daw.model.Booking;
import java.util.List;
import java.util.Collection;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDTO toDTO(Booking booking);

    List<BookingDTO> toDTOs(Collection<Booking> bookings);

    @Mapping(target = "id", ignore = true)
    Booking toDomain(BookingDTO bookingDTO);
}
