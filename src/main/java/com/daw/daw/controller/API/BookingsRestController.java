package com.daw.daw.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Collection;
import com.daw.daw.dto.BookingDTO;
import com.daw.daw.service.BookingsService;

/**
 * This class is a REST controller for handling booking-related API requests.
 * It is part of the web application project for the 2024-2025 academic year.
 * 
 * The controller provides endpoints for creating, retrieving, updating, and
 * deleting bookings.
 * It interacts with the service layer to perform these operations and returns
 * appropriate HTTP responses.
 * 
 * Example of a more complex method:
 * - `updateBooking`: This method handles HTTP PUT requests to update an
 * existing booking.
 * It takes a booking ID and booking details as input, validates the data, and
 * updates the booking in the database.
 * 
 * Note: Ensure that the necessary dependencies and configurations are in place
 * for the controller to function correctly.
 */

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsRestController {

    @Autowired
    private BookingsService bookingsService;

    @GetMapping("/")
    public Collection<BookingDTO> getAllBookings() {
        return bookingsService.findAll();
    }

    @GetMapping("/{id}")
    public BookingDTO getBooking(@PathVariable Long id) {
        return bookingsService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingsService.createBooking(bookingDTO));
    }

    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingsService.updateBooking(id, bookingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingsService.deleteBooking(id);
    }
}
