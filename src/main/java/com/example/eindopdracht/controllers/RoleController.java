package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.RoleDto;
import com.example.eindopdracht.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Handling of HTTP requests which returns the response directly to the client
@RestController
// Setting the endpoint as a standard, unless specified otherwise"/roles")
public class RoleController {

    private final RoleService roleService;

    // Constructor to inject the RoleService dependency
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Endpoint to retrieve all roles
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        // Call the service to retrieve all roles
        List<RoleDto> rDto = roleService.getAllRoles();
        // Return the list of roles and HTTP status code 200 (OK)
        return new ResponseEntity<>(rDto, HttpStatus.OK);
    }
}
