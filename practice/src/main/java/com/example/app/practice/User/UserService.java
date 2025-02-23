
package com.example.app.practice.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        if (user.getEmail() == null || user.getName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Name, email, and password must not be null");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        try {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));  // Encode the password before saving
            return userRepository.save(user);  // Save the user and return the saved user
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle the optimistic locking failure
            throw new RuntimeException("Optimistic locking failure while registering user", e);
        } catch (Exception e) {
            // Log the exception and rethrow it or handle it appropriately
            throw new RuntimeException("Error registering user", e);
        }
    }

    public String verify(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return "User is authenticated";
            } else {
                return "Authentication failed";
            }
        } catch (AuthenticationException e) {
            return "Authentication failed: " + e.getMessage();
        }
    }
}