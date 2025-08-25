package com.spring.expenses.expensestracker.security;


import com.petros.billsreminder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;  // Repository to access User data from the database

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // This method is called by Spring Security during the authentication process.
        // It tries to find a User entity by the username provided during login.

        return userRepository.findByUsername(username)    // Query the database for a user with the given username
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}

