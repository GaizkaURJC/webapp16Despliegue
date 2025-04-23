package com.daw.daw.security.jwt;


import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

/**
 * This service class handles user authentication and token management for the
 * application.
 * It provides methods for user login, token refresh, and logout
 * functionalities.
 * The login method authenticates the user and generates JWT tokens which are
 * stored in cookies.
 * The refresh method validates the refresh token and generates a new access
 * token.
 * The logout method clears the security context and removes the tokens from
 * cookies.
 */

@Service
public class UserLoginService {

	private static final Logger log = LoggerFactory.getLogger(UserLoginService.class);

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtTokenProvider jwtTokenProvider;

	public UserLoginService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	public ResponseEntity<AuthResponse> login(HttpServletResponse response, LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		
		String username = loginRequest.getEmail();
		UserDetails user = userDetailsService.loadUserByUsername(username);

		HttpHeaders responseHeaders = new HttpHeaders();
		String newAccessToken = jwtTokenProvider.generateAccessToken(user);
		String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);

		response.addCookie(buildTokenCookie(TokenType.ACCESS, newAccessToken));
		response.addCookie(buildTokenCookie(TokenType.REFRESH, newRefreshToken));

		AuthResponse loginResponse = new AuthResponse(AuthResponse.Status.SUCCESS,
				"Auth successful. Tokens are created in cookie.");
		return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
	}

	 public ResponseEntity<AuthResponse> refresh(HttpServletResponse response, String refreshToken) {
        try {
            Claims claims = jwtTokenProvider.validateTokens(refreshToken);
            if (claims == null) {
                throw new Exception("Invalid refresh token");
            }
            
            String username = claims.getSubject();
            UserDetails user = userDetailsService.loadUserByUsername(username);

            String newAccessToken = jwtTokenProvider.generateAccessToken(user);
            response.addCookie(buildTokenCookie(TokenType.ACCESS, newAccessToken));

            return ResponseEntity.ok()
                .body(new AuthResponse(AuthResponse.Status.SUCCESS, "New access token created"));
                
        } catch (Exception e) {
            log.error("Refresh token error", e);
            return ResponseEntity.status(401)
                .body(new AuthResponse(AuthResponse.Status.FAILURE, "Invalid refresh token"));
        }
    }

	public String logout(HttpServletResponse response) {
		SecurityContextHolder.clearContext();
		response.addCookie(removeTokenCookie(TokenType.ACCESS));
		response.addCookie(removeTokenCookie(TokenType.REFRESH));

		return "logout successfully";
	}

	private Cookie buildTokenCookie(TokenType type, String token) {
		Cookie cookie = new Cookie(type.cookieName, token);
		cookie.setMaxAge((int) type.duration.getSeconds());
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		return cookie;
	}

	private Cookie removeTokenCookie(TokenType type) {
		Cookie cookie = new Cookie(type.cookieName, "");
		cookie.setMaxAge(0);
		cookie.setHttpOnly(true);
		cookie.setPath("/");
		return cookie;
	}

public enum TokenType {
    ACCESS("access_token", Duration.ofHours(1)),
    REFRESH("refresh_token", Duration.ofDays(7));

    public final String cookieName;
    public final Duration duration;

    TokenType(String cookieName, Duration duration) {
        this.cookieName = cookieName;
        this.duration = duration;
    }
}
}
