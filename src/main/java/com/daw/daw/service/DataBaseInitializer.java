package com.daw.daw.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.time.LocalDateTime;
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
import com.daw.daw.repository.TicketRepository;
import com.daw.daw.model.Reserva;
import com.daw.daw.model.Ticket;

@Service
public class DataBaseInitializer {

        @Autowired
        private TicketRepository ticketRepository;
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
                Blob defUserImg = loadImage("img/defuser.webp");
                User admin = new User("admin", "admin@admin.com", "00011122", passwordEncoder.encode("admin"),
                                Arrays.asList("ADMIN","USER"), defUserImg);
                User user = new User("user", "user@user.com", "222111000", passwordEncoder.encode("user"),
                                Arrays.asList("USER"), defUserImg);
                LocalDateTime newDate = LocalDateTime.of(2025, 3, 15, 14, 30); // Año, Mes, Día, Hora, Minutos
                Ticket ticket = new Ticket("user@user.com", "12345678L", "Nombre", "Melendi", "Hombre", "user", "rock",
                                newDate);

                if (ticketRepository.findAll().isEmpty()) {
                        ticketRepository.save(ticket);
                }
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
                Event acdcd = new Event("AC-DC", "concert", "Los mejores rockeros de la hisotria de la musica",
                                loadImage("img/acdc.webp"), "rock");
                Event cruzCafune = new Event("Cruz Cafune", "concert", "Fiolo reza fiolo",
                                loadImage("img/cruzcafune.jpg"), "rap");
                Event pekkeErgo = new Event("Il Pekkeño & Ergo Pro", "concert",
                                "Los raperos mas duros de caravanchel, desde el barrio hacia madrid",
                                loadImage("img/PekkeErgo.jpg"), "rap");
                Event mykeTowers = new Event("Myke Towers", "concert",
                                "El trappero mas pegao del momento, viene a la joy", loadImage("img/mykeyowers.jpg"),
                                "trap");
                Event rollingStones = new Event("The Rolling Stones", "concert",
                                "El grupo mas mitico de rock de nuetra epoca viene a hacer mucho ruido!!",
                                loadImage("img/the.rolling.jpg"), "rock");
                Event yerayCortes = new Event("Yeray Cortes", "concert",
                                "La revelacion de la guittara española viene al teatro despues de ganar su primer oscar...",
                                loadImage("img/yerayy.png"), "flamenco");
                Event elFerias = new Event("David Ramzi Akka.Elferias", "concert",
                                "El gitano de moda viene a la joy a canatr su cancion, GITANO CABRON!",
                                loadImage("img/ferias.png"), "flamenco");
                Event ctangana = new Event("Ctanga", "concert",
                                "Uno de los mejores artistas de la capital...El madrileño!!",
                                loadImage("img/ctangana.jpg"), "flamenco");
                Event fitoFitipaldis = new Event("Fito & Fitipaldis", "concert",
                                "El mejor pop-rock de los 90 vienen a encogernois el corazon a la joy",
                                loadImage("img/fito.jpg"), "pop");
                Event theBeatles = new Event("The Beatles", "concert",
                                "Los primeros en denominar Rock a un estilo musical!!",
                                loadImage("img/TheBeatles.webp"), "rock");
                Event monchoChavea = new Event("Moncho Chavea", "concert", "El gitaneo mas sonado de españa",
                                loadImage("img/gitano2.jpg"), "flamenco");
                Event cigala = new Event("El Cigala", "concert", "El gitano mas fomoso del pais, despues de ferias!",
                                loadImage("img/gitano3.jpg"), "flamenco");
                Event melendi = new Event("El Milindri", "concert",
                                "Vuelve el milindri, aunque ha dejado los porros sigue siendo unico!!",
                                loadImage("img/melendi.jpg"), "pop");
                Event ochoYmedio = new Event("OCHOYMEDIO", "party",
                                "La mejor música indie de Madrid te espera cada viernes y sábado.|Vive el ambiente único de OCHOYMEDIO, donde la fiesta nunca termina.",
                                loadImage("img/ochoymedio.jpg"), "party");
                Event wasaby = new Event("WASABI", "party",
                                "La fiesta más picante de Buenos Aires aterriza en Madrid.|Chupitos gratis a las 4 y música que arde en cada rincón.",
                                loadImage("img/wasabyFest.jpg"), "party");
                Event bubuRoom = new Event("BUBU ROOM", "party",
                                "Tu sala favorita se convierte en un paraíso electrónico.|BUBU ROOM mezcla luces, DJs internacionales y un ambiente brutal.",
                                loadImage("img/BubuRoom.avif"), "party");

                if (comentsRepository.findAll().isEmpty()) {
                        Coments coments1 = new Coments(5, "Me encanto el concierto, la mejor noche de mi vida", "user", 5);
                        Coments coments2 = new Coments(4, "La musica era buena, pero la bebida era cara", "user", 6);
                        Coments coments3 = new Coments(3, "No me gusto mucho, la musica era muy rara", "user", 3);
                        Coments coments4 = new Coments(5, "La mejor fiesta de mi vida, repetire seguro", "user", 4);
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
                        eventRepository.save(pekkeErgo);
                        eventRepository.save(mykeTowers);
                        eventRepository.save(rollingStones);
                        eventRepository.save(yerayCortes);
                        eventRepository.save(elFerias);
                        eventRepository.save(ctangana);
                        eventRepository.save(fitoFitipaldis);
                        eventRepository.save(theBeatles);
                        eventRepository.save(monchoChavea);
                        eventRepository.save(cigala);
                        eventRepository.save(melendi);
                        eventRepository.save(acdcd);

                }
                if (reservaRepository.findAll().isEmpty()) {
                        Reserva reserva1 = new Reserva("Amancio Ortega", "zara@zara.com", "Zara", 1200,
                                        "Evento corporativo con barra libre de cerveza, vino y refrescos durante 4 horas",
                                        "pendiente");
                        Reserva reserva2 = new Reserva("Florentino Perez", "cristiano@mbappe.es", "Real Madrid CF",
                                        1500,
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
