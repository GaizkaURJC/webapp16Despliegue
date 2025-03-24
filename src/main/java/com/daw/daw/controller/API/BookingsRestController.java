package com.daw.daw.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Collection;
import com.daw.daw.dto.BookingDTO;
import com.daw.daw.service.BookingsService;

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
