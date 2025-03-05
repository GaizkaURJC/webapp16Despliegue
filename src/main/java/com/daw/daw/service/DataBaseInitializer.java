package com.daw.daw.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import jakarta.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.daw.daw.model.Coments;
import com.daw.daw.model.User;
import com.daw.daw.repository.ComentsRepository;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.model.Event;
import com.daw.daw.model.Image;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.ImageRepository;
import com.daw.daw.repository.ReservaRepository;
import com.daw.daw.model.Reserva;

@Service
public class DataBaseInitializer {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ComentsRepository comentsRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @PostConstruct
    public void init() throws IOException, URISyntaxException {

        User admin = new User("admin", "admin@admin.com", "00011122", passwordEncoder.encode("admin"),
                Arrays.asList("ADMIN"));
        User user = new User("user", "user@user.com", "222111000", passwordEncoder.encode("user"),
                Arrays.asList("USER"));

        // SAMPLE USERS
        if (UserRepository.findByName(admin.getNombre()).isEmpty()
                && UserRepository.findByName(user.getNombre()).isEmpty()) {
            UserRepository.save(admin);
            UserRepository.save(user);
        }
        if (imageRepository.findAll().isEmpty()) {
            Blob videoIndex = loadImage("img/index.mp4");
            Image imageIndex = new Image(videoIndex, "index");
            imageRepository.save(imageIndex);
        }
        Event cruzCafune = new Event("Cruz Cafune", "concert", "Fiolo reza fiolo", loadImage("img/cruzcafune.jpg"));
        Event ochoYmedio = new Event("OCHOYMEDIO", "party",
                "La mejor musica indie, todos los viernes y sabados en tu discoteca favorita",
                loadImage("img/ochoymedio.jpg"));
        Event wasaby = new Event("WASABI", "party",
                "La fiesta mas picante de buenos aires, aterriza en madrid, chupitos gratis a las 4...",
                loadImage("img/wasabyFest.jpg"));
        Event bubuRoom = new Event("BUBU ROOM", "party",
                "La fiesta mas picante de buenos aires, aterriza en madrid, chupitos gratis a las 4...",
                loadImage("img/BubuRoom.avif"));
        if (comentsRepository.findAll().isEmpty()) {
            Coments coments1 = new Coments(5, "Me encanto el concierto, la mejor noche de mi vida", "user");
            Coments coments2 = new Coments(4, "La musica era buena, pero la bebida era cara", "user");
            Coments coments3 = new Coments(3, "No me gusto mucho, la musica era muy rara", "user");
            Coments coments4 = new Coments(5, "La mejor fiesta de mi vida, repetire seguro", "user");
            comentsRepository.save(coments1);
            comentsRepository.save(coments2);
            comentsRepository.save(coments3);
            comentsRepository.save(coments4);
        }
        if (!eventRepository.existsByTitle(ochoYmedio.getTitle()) &&
                eventRepository.findByTitle(bubuRoom.getTitle()).isEmpty() &&
                eventRepository.findByTitle(wasaby.getTitle()).isEmpty()
                && !eventRepository.existsByTitle(cruzCafune.getTitle())) {
            eventRepository.save(ochoYmedio);
            eventRepository.save(bubuRoom);
            eventRepository.save(wasaby);
            eventRepository.save(cruzCafune);
        }
        if (reservaRepository.findAll().isEmpty()) {
            Reserva reserva1 = new Reserva("Amancio Ortega", "zara@zara.com", "Zara", 1200,
                    "Evento corporativo con barra libre de cerveza, vino y refrescos durante 4 horas", "pendiente");
            Reserva reserva2 = new Reserva("Florentino Perez", "cristiano@mbappe.es", "Real Madrid CF", 1500,
                    "Evento para celebrar una champions", "pendiente");
            reservaRepository.save(reserva1);
            reservaRepository.save(reserva2);
        }
    }

    private Blob loadImage(String path) {
        try {
            InputStream inputStream = new ClassPathResource("static/" + path).getInputStream();
            return BlobProxy.generateProxy(inputStream, inputStream.available());
        } catch (IOException e) {
            System.err.println("⚠ No se pudo cargar la imagen: " + path + ". Usando imagen por defecto.");
            return BlobProxy.generateProxy(new byte[0]);
        }
    }
}
