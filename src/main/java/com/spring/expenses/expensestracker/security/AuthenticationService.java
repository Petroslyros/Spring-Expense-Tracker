package com.spring.expenses.expensestracker.security;

import com.spring.expenses.expensestracker.dto.AuthenticationRequestDTO;
import com.spring.expenses.expensestracker.dto.AuthenticationResponseDTO;
import com.spring.expenses.expensestracker.model.User;
import com.spring.expenses.expensestracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JWTService jwtService;                     // JWT utility service to generate tokens
    private final UserRepository userRepository;                   // User repository for database access (not used here directly)
    private final AuthenticationManager authenticationManager; // Spring Security component to perform authentication

    /**
     * Authenticates user credentials and returns AuthenticationResponseDTO with JWT token.
     */
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto) {
        // Try authenticating the user with username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );

        // If authentication succeeds, get authenticated User object from Authentication principal
        User user = (User) authentication.getPrincipal();

        // Generate JWT token with username and role info
        String token = jwtService.generateToken(authentication.getName(), user.getRole().name());

        // Return response DTO with user's first and last names plus the JWT token
        return new AuthenticationResponseDTO(user.getUsername(), token);
    }
}


