package com.spring.expenses.expensestracker.controller;


import com.spring.expenses.expensestracker.dto.AuthenticationRequestDTO;
import com.spring.expenses.expensestracker.dto.AuthenticationResponseDTO;
import com.spring.expenses.expensestracker.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")  // Base URL path for authentication endpoints
@RequiredArgsConstructor      // Lombok generates constructor for final fields
public class AuthRestController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")  // POST /api/auth/login endpoint for user login/authentication
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {

        // Delegate authentication to AuthenticationService, which returns user info + JWT token
        AuthenticationResponseDTO authenticationResponseDTO = authenticationService.authenticate(authenticationRequestDTO);

        // Return HTTP 200 OK with the authentication response (token + user details)
        return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
    }
}

