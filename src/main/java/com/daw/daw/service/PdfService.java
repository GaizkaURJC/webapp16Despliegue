package com.daw.daw.service;

import com.daw.daw.model.Booking;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import com.daw.daw.model.Ticket;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generarPdfReserva(Booking reserva) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph("Confirmación de Reserva", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph("Nombre: " + reserva.getUserName()));
        document.add(new Paragraph("Email: " + reserva.getUserEmail()));
        document.add(new Paragraph("Empresa: " + reserva.getBussinesName()));
        document.add(new Paragraph("Número de personas: " + reserva.getCapacity()));
        document.add(new Paragraph("Descripción: " + reserva.getEventDescript()));
        document.add(new Paragraph("Estado: " + reserva.getStatus()));
        document.close();

        return out.toByteArray();
    }

    public byte [] generarPdfTicket (Ticket ticket) throws DocumentException{
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        

        document.open();
        document.add(new Paragraph("Confirmación de Ticket", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph("Nombre: " + ticket.getUserOwner()));
        document.add(new Paragraph("Email: " + ticket.getUserEmail()));
        document.add(new Paragraph("DNI: " + ticket.getDni()));
        document.add(new Paragraph("Nombre del ticket: " + ticket.getTicketName()));
        document.add(new Paragraph("Género: " + ticket.getGender()));
        document.add(new Paragraph("Categoría: " + ticket.getCategory()));
        document.close();
        return out.toByteArray();

    }
}

