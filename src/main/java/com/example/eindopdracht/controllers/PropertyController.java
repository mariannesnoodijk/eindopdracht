package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.services.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Handles HTTP requests and returns the response directly to the client
@RequestMapping("/properties") // Using @RequestMapping sets the endpoint as a standard, unless specified otherwise
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping // This method handles HTTP POST requests to the /properties endpoint creating a property
    public ResponseEntity<PropertyDto> createProperty(@Valid @RequestBody PropertyDto propertyDto) {
        PropertyDto newProperty = propertyService.createProperty(propertyDto);
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    @GetMapping("/{propertyId}") // This method handles HTTP GET requests to the /properties/{propertyId} endpoint, where {id} is a path variable representing the property ID
    public ResponseEntity<PropertyDto> getOneProperty(@PathVariable Long propertyId) {
        PropertyDto pDto = propertyService.getProperty(propertyId);
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @GetMapping // This method handles HTTP GET requests to the /properties endpoint
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        List<PropertyDto> pDto = propertyService.getAllProperties();
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    @DeleteMapping("/{propertyId}") // This method handles HTTP DELETE requests to the /properties/{propertyId} endpoint, where {id} is a path variable representing the property ID
    public ResponseEntity<PropertyDto> deleteProperty(@PathVariable Long propertyId) {
        propertyService.deleteProperty(propertyId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
