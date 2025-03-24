package com.daw.daw.controller.MVC;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.daw.daw.model.Booking;
import com.daw.daw.repository.BookingRepository;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.daw.daw.service.PdfService;
import com.lowagie.text.DocumentException;

/**
 * This controller class handles the MVC pattern for
 * managing reservations
 * within the web application. It is part of the "com.daw.daw.controller.MVC"
 * package.
 * 
 * The class is responsible for processing incoming web requests related to
 * reservations,
 * interacting with the service layer to perform business logic, and returning
 * appropriate
 * views to the client.
 * 
 * Note: Specific methods within this class handle various CRUD operations and
 * other
 * functionalities related to reservations.
 */

@Controller
@RequestMapping("/reserva/")
public class ReservaMVCController {

    @Autowired
    private BookingRepository reservaRepository;

    @Autowired
    private PdfService pdfService;

    @PostMapping("request")
    public void requestEvent(
            @RequestParam("userName") String name,
            @RequestParam("userEmail") String email,
            @RequestParam("bussinesName") String bussinesname,
            @RequestParam("num_personas") int pax,
            @RequestParam("eventDescript") String description,
            HttpServletResponse response) throws IOException, DocumentException {

        Booking reserva = new Booking(name, email, bussinesname, pax, description, "pendiente");
        reservaRepository.save(reserva);

        // Generate PDF
        byte[] pdfBytes = pdfService.generarPdfReserva(reserva);

        // HTTPS configuration for the response
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=reserva_" + reserva.getUserName() + ".pdf");
        response.getOutputStream().write(pdfBytes);
        response.flushBuffer();
    }

    @PostMapping("aceptar")
    public String aceptarReserva(@RequestParam Long id) {
        Optional<Booking> reserva = reservaRepository.getBookingById(id);
        if (reserva.isPresent()) {
            reserva.get().setStatus("aceptada");
            reservaRepository.save(reserva.get());
            return "redirect:/admin";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("rechazar")
    public String rechazarReserva(@RequestParam Long id) {
        Optional<Booking> reserva = reservaRepository.getBookingById(id);
        if (reserva.isPresent()) {
            reserva.get().setStatus("rechazada");
            reservaRepository.save(reserva.get());
            return "redirect:/admin";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("deleteReserva")
    public String deleteReserva(@RequestParam Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/admin";

    }

}
