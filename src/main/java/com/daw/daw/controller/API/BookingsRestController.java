package com.daw.daw.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Collection;
import com.daw.daw.dto.BookingDTO;
import com.daw.daw.service.BookingsService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingsRestController {

    @Autowired
    private BookingsService bookingsService;

    @Operation(summary = "Get all the bookings done")
    @GetMapping("/")
    public Collection<BookingDTO> getAllBookings() {
        return bookingsService.findAll();
    }

    @Operation(summary = "Get a single booking by its id")
    @GetMapping("/{id}")
    public BookingDTO getBooking(@PathVariable Long id) {
        return bookingsService.findById(id);
    }

    @Operation(summary = "Create a new booking")
    @PostMapping("/")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingsService.createBooking(bookingDTO));
    }

    @Operation(summary = "Update a booking that already exists")
    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingsService.updateBooking(id, bookingDTO);
    }

    @Operation(summary = "Delete a booking by its id")
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingsService.deleteBooking(id);
    }
}
