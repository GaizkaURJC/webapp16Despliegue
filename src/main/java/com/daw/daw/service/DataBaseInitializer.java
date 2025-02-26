package com.daw.daw.service;

import java.io.IOException;
import java.net.URISyntaxException;
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

@Service
public class DataBaseInitializer {
    
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        //SAMPLE USERS
        UserRepository.save(new User("admin","admin@admin.com","00011122",passwordEncoder.encode("admin"),Arrays.asList("ADMIN")));
        UserRepository.save(new User("user","user@user.com","222111000",passwordEncoder.encode("user"),Arrays.asList("USER")));

        // Verificar el número de usuarios
        long userCount = UserRepository.count();
        System.out.println("Número de usuarios en la base de datos: " + userCount);
    
    }
}
