package com.daw.daw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class Security {

    @Autowired
    private RepositoryUserDetailsService userDetailsService;

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
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authenticationProvider(authenticationProvider());

    http.securityContext(securityContext -> securityContext
        .securityContextRepository(securityContextRepository()) // Habilita el almacenamiento en sesión
    );

    http.authorizeHttpRequests(authorize -> authorize
            //PUBLIC PAGES
            .requestMatchers("/").permitAll()
            .requestMatchers("/css/**").permitAll()
            .requestMatchers("/img/**").permitAll()
            .requestMatchers("/js/**").permitAll()
            .requestMatchers("/videos/**").permitAll()
            .requestMatchers("/imgEvent/**").permitAll()
            .requestMatchers("/users/authenticate").permitAll()
            .requestMatchers("/users/create").permitAll()
            .requestMatchers("/favicon/**").permitAll()
            .requestMatchers("/events/**").permitAll()
            .requestMatchers("/paginaDetalleConcierto/**").permitAll()
            .requestMatchers("/clubing/**").permitAll()
            .requestMatchers("/favicon.ico/**").permitAll()
            .requestMatchers("/error").permitAll()
            //PRIVATE PAGES
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/perfil/**").hasRole("USER")
            .requestMatchers("/paginaperfil/**").hasRole("USER")
            .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/tickets/buyTicket").hasRole("USER")
            .requestMatchers("/tickets/**").hasRole("USER")
            .requestMatchers("/events/partyCreate").hasRole("ADMIN")
            .requestMatchers("/events/conciertoCreate").hasRole("ADMIN")
            .requestMatchers("/reserva/aceptar").hasRole("ADMIN")
            .requestMatchers("/reserva/request").hasRole("USER")
            .requestMatchers("/application/pdf").hasRole("USER")     
            .requestMatchers("/coments/create").hasRole("USER")
            .requestMatchers("/events/deleteEvent").hasRole("ADMIN")
            .requestMatchers("/reserva/aceptar").hasRole("ADMIN")
            .requestMatchers("/reserva/rechazar").hasRole("ADMIN")
            .requestMatchers("/reserva/deleteReserva").hasRole("ADMIN")
            .requestMatchers("/users/deleteUser").hasRole("ADMIN")
            .requestMatchers("/events/conciertoCreate").hasRole("ADMIN")
            .requestMatchers("/events/partyCreate").hasRole("ADMIN")
            .requestMatchers("/events/**").hasRole("ADMIN")
            .requestMatchers("users/profileImg/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/users/updateUser").hasRole("USER")
    )
        .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .defaultSuccessUrl("/", true) // nos manda al home después del login
            .permitAll())
        .logout(logout -> logout
            .logoutUrl("/users/logout").permitAll()
            .logoutSuccessUrl("/").permitAll());

    return http.build();
}
}
