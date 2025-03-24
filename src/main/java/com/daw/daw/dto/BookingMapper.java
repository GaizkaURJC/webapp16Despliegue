package com.daw.daw.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.daw.daw.model.Booking;
import java.util.List;
import java.util.Collection;

/**
 * This file contains the BookingMapper class, which is responsible for mapping
 * booking-related data transfer objects (DTOs) to and from entity objects.
 * It facilitates the conversion between different layers of the application,
 * ensuring that data is correctly transformed and transferred.
 */

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingDTO toDTO(Booking booking);

    List<BookingDTO> toDTOs(Collection<Booking> bookings);

    @Mapping(target = "id", ignore = true)
    Booking toDomain(BookingDTO bookingDTO);
}
