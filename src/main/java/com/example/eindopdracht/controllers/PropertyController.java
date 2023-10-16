package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.services.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/properties") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        List<PropertyDto> pDto = propertyService.getAllProperties();
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDto> getOneProperty(@PathVariable Long id) {
        PropertyDto pDto = propertyService.getProperty(id);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto propertyDto) {
        PropertyDto newProperty = propertyService.createProperty(propertyDto);
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PropertyDto> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
