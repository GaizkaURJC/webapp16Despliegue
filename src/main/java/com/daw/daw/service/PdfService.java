package com.daw.daw.service;

import com.daw.daw.model.Reserva;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generarPdfReserva(Reserva reserva) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph("Confirmación de Reserva", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
        document.add(new Paragraph("Nombre: " + reserva.getUserName()));
        document.add(new Paragraph("Email: " + reserva.getUserEmail()));
        document.add(new Paragraph("Empresa: " + reserva.getBussinesName()));
        document.add(new Paragraph("Número de personas: " + reserva.getNum_personas()));
        document.add(new Paragraph("Descripción: " + reserva.getEventDescript()));
        document.add(new Paragraph("Estado: " + reserva.getEstado()));
        document.close();

        return out.toByteArray();
    }
}

