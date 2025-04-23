package com.daw.daw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import com.daw.daw.dto.BookingDTO;
import com.daw.daw.dto.BookingMapper;
import com.daw.daw.model.Booking;
import com.daw.daw.repository.BookingRepository;

/**
 * This class is part of the service layer in the web application.
 * It is responsible for handling the business logic related to bookings.
 * The class provides methods to manage bookings, including creating, updating,
 * and retrieving booking information.
 */

@Service
public class BookingsService {

    @Autowired
    private BookingRepository booking;

    @Autowired
    private BookingMapper bookingMapper;

    public Collection<BookingDTO> findAll() {
        return bookingMapper.toDTOs(booking.findAll());
    }

    public BookingDTO findById(Long id) {
        return bookingMapper.toDTO(booking.findById(id).orElseThrow());
    }

    public BookingDTO createBooking(BookingDTO reservaDTO) {
        Booking reserva = bookingMapper.toDomain(reservaDTO);
        return bookingMapper.toDTO(this.booking.save(reserva));
    }

    public void deleteBooking(Long id) {
        booking.deleteById(id);
    }

    public BookingDTO updateBooking(Long id, BookingDTO reservaDTO) {
        Booking reserva = bookingMapper.toDomain(reservaDTO);
        reserva.setId(id);
        return bookingMapper.toDTO(this.booking.save(reserva));
    }
}
