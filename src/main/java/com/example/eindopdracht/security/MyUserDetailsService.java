package com.example.eindopdracht.security;

import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    // Repository for user data
    private final UserRepository userRepos;

    // Constructor to inject the UserRepository dependency
    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }

    // Load user details by username for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve the user by username from the UserRepository
        Optional<User> ou = userRepos.findById(username);

        // If the user is found, create and return UserDetails
        if (ou.isPresent()) {
            User user = ou.get();
            return new MyUserDetails(user);
        } else {
            // If the user is not found, throw UsernameNotFoundException
            throw new UsernameNotFoundException(username);
        }
    }
}
