package com.daw.daw.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daw.daw.model.User;
import com.daw.daw.repository.UserRepository;
import com.daw.daw.model.Event;
import com.daw.daw.model.Image;
import com.daw.daw.repository.EventRepository;
import com.daw.daw.repository.ImageRepository;

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

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        User admin = new User("admin","admin@admin.com","00011122",passwordEncoder.encode("admin"),Arrays.asList("ADMIN"));
        User user = new User("user","user@user.com","222111000",passwordEncoder.encode("user"),Arrays.asList("USER")); 
        
        //SAMPLE USERS
        if (UserRepository.findByName(admin.getNombre()).isEmpty() && UserRepository.findByName(user.getNombre()).isEmpty()) {
            UserRepository.save(admin);
            UserRepository.save(user);
        }

        if (imageRepository.findAll().isEmpty()) {
            Blob videoIndex = loadImage("img/index.mp4");
            Image imageIndex = new Image(videoIndex, "index");
            imageRepository.save(imageIndex);
        }

        Event cruzCafune = new Event("Cruz Cafune", "concert","Fiolo reza fiolo", loadImage("img/cruzcafune.jpg"));
        Event ochoYmedio = new Event("OCHOYMEDIO", "party","La mejor musica indie, todos los viernes y sabados en tu discoteca favorita", loadImage("img/ochoymedio.jpg"));  
        Event bubuRoom = new Event("BUBU ROOM", "party","Una noche llena de flow, dembow, y de los bailes mas latinos de la capital!", loadImage("img/BubuRoom.avif")); 
        Event wasaby = new Event("WASABI", "party","La fiesta mas picante de buenos aires, aterriza en madrid, chupitos gratis a las 4...", loadImage("img/wasabyFest.jpg"));
        
        if (!eventRepository.existsByTitle(ochoYmedio.getTitle()) && 
             eventRepository.findByTitle(bubuRoom.getTitle()).isEmpty() && 
             eventRepository.findByTitle(wasaby.getTitle()).isEmpty() 
            && !eventRepository.existsByTitle(cruzCafune.getTitle())) {

            eventRepository.save(ochoYmedio);
            eventRepository.save(bubuRoom);
            eventRepository.save(wasaby);
            eventRepository.save(cruzCafune);
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
