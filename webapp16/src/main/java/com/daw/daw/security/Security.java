package com.daw.daw.security;

import org.springframework.http.HttpMethod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;

import com.daw.daw.security.jwt.JwtRequestFilter;
import com.daw.daw.security.jwt.UnauthorizedHandlerJwt;

/**
 * This file is part of the security package for the web application.
 * It contains security-related configurations and settings to ensure
 * the application is protected against various security threats.
 * The configurations may include authentication, authorization,
 * and other security measures.
 */

@Configuration
@EnableWebSecurity
public class Security {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

    @Autowired
    private RepositoryUserDetailsService userDetailsService;

    @Autowired
    private CSRFHandlerConfiguration CSRFHandlerConfiguration;

    // Remove the injection of the AuthenticationManager in the constructor
    public Security() {
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @Primary
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**")
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of(
                    "http://localhost:4200",
                    "https://localhost",
                    "http://localhost"
                ));
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(true);
                config.setExposedHeaders(List.of("Authorization"));
                return config;
            }))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/events/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/comments/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/csrf-token").permitAll()

                        // USER
                        .requestMatchers(HttpMethod.POST, "/api/v1/bookings/").authenticated() // Cambio específico aquí
                        .requestMatchers(HttpMethod.POST, "/api/v1/comments/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/tickets/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/me").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/MyTickets").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "api/v1/tickets/MyTicket/{id}").hasRole("USER")
                        .requestMatchers("/new/**").permitAll() // Permitir acceso público a /new/**


                        // ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/events/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/events/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/comments/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/tickets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bookings/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/bookings/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/{id}/image").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users/{id}/image").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/stats/gender/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/{id}/image").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN") // delete only for ADMIN
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(2) // Change the order to avoid conflicts
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests(authorize -> authorize

                .requestMatchers("/v3/api-docs*/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()

                .requestMatchers("/", "/css/**", "/img/**", "/js/**", "/videos/**", "/imgEvent/**").permitAll()
                .requestMatchers("/users/authenticate", "/users/create", "/favicon/**", "/events/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/perfil/**", "/paginaperfil/**", "/users/**", "/comments/**")
                .hasAnyRole("USER", "ADMIN")
                // SPA
                .requestMatchers("/spa/**").permitAll()
                .anyRequest().permitAll());
        http.formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll());

        http.logout(logout -> logout
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .permitAll());

        return http.build();
    }
}
