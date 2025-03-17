package com.daw.daw.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
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

import com.daw.daw.security.jwt.JwtRequestFilter;
import com.daw.daw.security.jwt.UnauthorizedHandlerJwt;
@Configuration
@EnableWebSecurity
public class Security {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	RepositoryUserDetailsService userDetailsService;

	@Autowired
	private UnauthorizedHandlerJwt unauthorizedHandlerJwt;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	@Order(1)
	public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
		
		http.authenticationProvider(authenticationProvider());
		
		http
			.securityMatcher("/api/**")
			.exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandlerJwt));
		
		http
			.authorizeHttpRequests(authorize -> authorize
                    // PRIVATE ENDPOINTS
                    .requestMatchers(HttpMethod.POST,"/api/v1/users").hasRole("USER")
                    .requestMatchers(HttpMethod.PUT,"/api/**").hasRole("USER")
                    .requestMatchers(HttpMethod.DELETE,"/api/v1/users/**").hasRole("ADMIN")
					// PUBLIC ENDPOINTS
					.anyRequest().permitAll()
			);
		
        // Disable Form login Authentication
        http.formLogin(formLogin -> formLogin.disable());

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf(csrf -> csrf.disable());

        // Disable Basic Authentication
        http.httpBasic(httpBasic -> httpBasic.disable());

        // Stateless session
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Add JWT Token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	@Order(1)
	public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {

		http.authenticationProvider(authenticationProvider());

		http
				.authorizeHttpRequests(authorize -> authorize
						// PUBLIC PAGES
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
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        // API ROUTES
                        .requestMatchers("/api/v1/**").authenticated()
                        // PRIVATE PAGES
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
