package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.AuthDto;
import com.example.eindopdracht.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Handling of HTTP requests which returns the response directly to the client
@RestController
// Setting the endpoint as a standard, unless specified otherwise
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    // Constructor to inject the AuthenticationManager and JwtService dependencies
    public AuthController(AuthenticationManager man, JwtService service) {
        this.authManager = man;
        this.jwtService = service;
    }

    // Endpoint for user authentication
    @PostMapping
    public ResponseEntity<Object> signIn(@Valid @RequestBody AuthDto authDto) {
        // Creating a UsernamePasswordAuthenticationToken for authentication
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

        try {
            // Authenticating the user with the provided credentials
            Authentication auth = authManager.authenticate(up);

            // Extracting UserDetails from the authenticated user
            UserDetails ud = (UserDetails) auth.getPrincipal();

            // Generating a JWT token for the authenticated user
            String token = jwtService.generateToken(ud);

            // Returning the JWT token in the response body with HTTP status code 200 (OK)
            return ResponseEntity.ok()
                    .body("Bearer " + token);
        }
        catch (AuthenticationException ex) {
            // Handling authentication failure and returning HTTP status code 401 (Unauthorized)
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
