package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.services.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handling of HTTP requests which returns the response directly to the client
@RestController
// Setting the endpoint as a standard, unless specified otherwise
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    // Constructor to inject the PropertyService dependency
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    // Endpoint to create a new property
    @PostMapping
    public ResponseEntity<PropertyDto> createProperty(@Valid @RequestBody PropertyDto propertyDto) {
        // Call the service to create a new property
        PropertyDto newProperty = propertyService.createProperty(propertyDto);
        // Return the new property and HTTP status code 201 (Created)
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    // Endpoint to retrieve a single property by ID
    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyDto> getOneProperty(@PathVariable Long propertyId) {
        // Call the service to retrieve a property by ID
        PropertyDto pDto = propertyService.getProperty(propertyId);
        // Return the property and HTTP status code 200 (OK)
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    // Endpoint to retrieve all properties
    @GetMapping
    public ResponseEntity<List<PropertyDto>> getAllProperties() {
        // Call the service to retrieve all properties
        List<PropertyDto> pDto = propertyService.getAllProperties();
        // Return the list of properties and HTTP status code 200 (OK)
        return new ResponseEntity<>(pDto, HttpStatus.OK);
    }

    // Endpoint to update the favorite status of a property
    @PatchMapping("/{propertyId}/favorite")
    public ResponseEntity<PropertyDto> updateFavoriteStatus(@PathVariable Long propertyId, @RequestBody Boolean isFavorite) {
        // Call the service to update the favorite status of a property
        PropertyDto updatedPropertyDto = propertyService.updateFavoriteStatus(propertyId, isFavorite);
        // Return the updated property and HTTP status code 200 (OK)
        return new ResponseEntity<>(updatedPropertyDto, HttpStatus.OK);
    }

    // Endpoint to delete a property by ID
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<PropertyDto> deleteProperty(@PathVariable Long propertyId) {
        // Call the service to delete a property by ID
        propertyService.deleteProperty(propertyId);
        // Return HTTP status code 204 (No Content) indicating successful deletion
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
