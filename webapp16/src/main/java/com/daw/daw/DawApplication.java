package com.daw.daw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * This is the main class for the DawApplication, a Spring Boot application.
 * It serves as the entry point for the application and is responsible for
 * bootstrapping and launching the Spring context.
 * 
 * The @SpringBootApplication annotation indicates that this is a Spring Boot
 * application.
 * The @EnableSpringDataWebSupport annotation enables support for Spring Data
 * web features,
 * with page serialization mode set to VIA_DTO.
 */

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)

public class DawApplication {

    public static void main(String[] args) {
        SpringApplication.run(DawApplication.class, args);
    }
}