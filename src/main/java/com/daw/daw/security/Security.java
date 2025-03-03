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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/","/img/{id}","/perfil","/paginaDetalleConcierto/{id}","/staticimg/vid", "/clubbing", "/paginaDetalleConcierto", "/register", "/error", "/css/**", "/js/**", "/images/**", "/users/create", "/users/authenticate","/admin","coments/create","img/index.mp4").permitAll()
                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                .loginPage("/users/login")
                .defaultSuccessUrl("/")
                .permitAll())
            .logout(logout -> logout
                .permitAll());
                http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}