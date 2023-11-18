package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.AccountDto;
import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.Role;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.AccountRepository;
import com.example.eindopdracht.repositories.RoleRepository;
import com.example.eindopdracht.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // Constructor to inject dependencies
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, AccountRepository accountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // Create a new user
    public User createUser(UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        List<Role> userRoles = newUser.getRoles();
        for (String rolename : userDto.getRoles()) {
            // Find and add roles to the user
            Optional<Role> or = roleRepository.findById(rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }
        }
        // Save the user
        return userRepository.save(newUser);
    }

    // Retrieve a user by ID
    public UserDto getUser(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User u = user.get();
            UserDto uDto = new UserDto();
            userToUserDto(u, uDto);
            return (uDto);
        } else {
            throw new IdNotFoundException("User not found with ID: " + userId);
        }
    }

    // Retrieve all users
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        // Convert User entities to UserDto
        for (User u : users) {
            UserDto uDto = new UserDto();
            userToUserDto(u, uDto);
            userDtos.add(uDto);
        }
        return userDtos;
    }

    // Helper method to convert User to UserDto
    private static void userToUserDto(User u, UserDto uDto) {
        uDto.setUsername(u.getUsername());
        uDto.setPassword(u.getPassword());
        ArrayList<String> roles = new ArrayList<>();
        for (Role role : u.getRoles()){
            roles.add(role.getRolename());
        }
        uDto.setRoles(roles.toArray(new String [0]));
    }
}