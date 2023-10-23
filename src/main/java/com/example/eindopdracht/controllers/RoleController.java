package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.dto.RoleDto;
import com.example.eindopdracht.models.Role;
import com.example.eindopdracht.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController // Handles HTTP requests and returns the response directly to the client
@RequestMapping("/roles") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping // This method handles HTTP GET requests to the /roles endpoint
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> rDto = roleService.getAllRoles();
        return new ResponseEntity<>(rDto, HttpStatus.OK);
    }

}
