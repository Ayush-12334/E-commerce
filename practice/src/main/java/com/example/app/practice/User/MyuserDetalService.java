
package com.example.app.practice.User;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class MyuserDetalService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user1 = userRepository.findByName(name); // Ensure user1 is of type User
        if (user1 == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new Userprinciple(user1);
    }
}