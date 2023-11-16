package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.RoleDto;
import com.example.eindopdracht.models.Role;
import com.example.eindopdracht.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    // Constructor to inject the RoleRepository dependency
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Retrieve all roles
    public List<RoleDto> getAllRoles() {
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role r : roleRepository.findAll()) {
            RoleDto rdto = new RoleDto();
            rdto.setRolename(r.getRolename());
            roleDtos.add(rdto);
        }
        return roleDtos;
    }
}
