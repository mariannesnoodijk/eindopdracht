package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.UserDto;
import com.example.eindopdracht.models.Role;
import com.example.eindopdracht.models.User;
import com.example.eindopdracht.repositories.RoleRepository;
import com.example.eindopdracht.repositories.UserRepository;
import com.example.eindopdracht.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder encoder) {

        this.userService = userService;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;



    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> uDto = userService.getAllUsers();
        return new ResponseEntity<>(uDto, HttpStatus.OK);
    }


    @PostMapping
    public String createUser(@RequestBody UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(encoder.encode(userDto.getPassword()));

        Set<Role> userRoles = newUser.getRoles();
        for (String rolename : userDto.getRoles()) {
            Optional<Role> or = roleRepository.findById("ROLE_" + rolename);
            if (or.isPresent()) {
                userRoles.add(or.get());
            }
        }

        userRepository.save(newUser);

        return "Done";
    }
}
