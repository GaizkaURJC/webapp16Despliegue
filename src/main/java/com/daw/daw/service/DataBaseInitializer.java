package com.daw.daw.service;

import java.io.IOException;
import java.net.URISyntaxException;
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

import com.daw.daw.model.Coments;
import com.daw.daw.model.User;
import com.daw.daw.repository.ComentsRepository;
import com.daw.daw.repository.UserRepository;

@Service
public class DataBaseInitializer {
    
    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private ComentsRepository ComentsRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException, URISyntaxException{

        if (UserRepository.count() == 0) {
            //SAMPLE USERS
        UserRepository.save(new User("admin","admin@admin.com","00011122",passwordEncoder.encode("admin"),Arrays.asList("ADMIN")));
        UserRepository.save(new User("user","user@user.com","222111000",passwordEncoder.encode("user"),Arrays.asList("USER")));    
        }
        if (ComentsRepository.count()==0) {
            //SAMPLE COMENTS
        ComentsRepository.save(new Coments(2,"soy rico, porque soy de cañada y de derechas","admin"));
        ComentsRepository.save(new Coments(3,"El athletic club es bastrante mejor que la real sociedad","user"));
        ComentsRepository.save(new Coments(4,"Es el mejor, una pena que sea de murcia", "admin"));
        ComentsRepository.save(new Coments(5,"Cruzzi cafu!!!!", "user"));

        }
    }
}
