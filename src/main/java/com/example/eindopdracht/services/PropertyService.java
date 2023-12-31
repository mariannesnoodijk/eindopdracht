package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Property;
import com.example.eindopdracht.repositories.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    // Constructor to inject the PropertyRepository dependency
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    // Create a new property
    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = new Property();
        propertyDtoToProperty(propertyDto, property);

        // Save the property
        Property savedProperty = propertyRepository.save(property);

        // Convert the saved property to PropertyDto
        PropertyDto savedPropertyDto = new PropertyDto();
        propertyToPropertyDto(savedProperty, savedPropertyDto);

        return savedPropertyDto;
    }

    // Retrieve a property by ID
    public PropertyDto getProperty(Long propertyId) {
        Optional<Property> property = propertyRepository.findById(propertyId);
        if (property.isPresent()) {
            Property p = property.get();
            PropertyDto pDto = new PropertyDto();
            propertyToPropertyDto(p, pDto);
            return pDto;
        } else {
            throw new IdNotFoundException("Property not found with ID: " + propertyId);
        }
    }

    // Retrieve all properties
    public List<PropertyDto> getAllProperties() {

        List<Property> properties = propertyRepository.findAll();
        List<PropertyDto> propertyDtos = new ArrayList<>();

        // Convert Property entities to PropertyDto
        for (Property p : properties) {
            PropertyDto pDto = new PropertyDto();
            propertyToPropertyDto(p, pDto);

            propertyDtos.add(pDto);
        }
        return propertyDtos;
    }

    // Update the favorite status of a property
    public PropertyDto updateFavoriteStatus(Long propertyId, Boolean isFavorite) {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isPresent()) {
            Property property = optionalProperty.get();
            property.setIsFavorite(isFavorite);
            propertyRepository.save(property);

            PropertyDto propertyDto = new PropertyDto();
            propertyToPropertyDto(property, propertyDto);
            propertyDto.setIsFavorite(isFavorite);

            return propertyDto;
        } else {
            throw new IdNotFoundException("Property not found with ID: " + propertyId);
        }
    }

    // Delete a property by ID
    public String deleteProperty(@RequestBody Long propertyId) {
        propertyRepository.deleteById(propertyId);
        return "Property successfully deleted";
    }

    // Helper method to convert Property to PropertyDto
    private static void propertyToPropertyDto(Property p, PropertyDto pDto) {
        pDto.setPropertyId(p.getPropertyId());
        pDto.setAddress(p.getAddress());
        pDto.setPrice(p.getPrice());
        pDto.setDescription(p.getDescription());
    }

    // Helper method to convert PropertyDto to Property
    private static void propertyDtoToProperty(PropertyDto propertyDto, Property property) {
        property.setPropertyId(propertyDto.getPropertyId());
        property.setAddress(propertyDto.getAddress());
        property.setPrice(propertyDto.getPrice());
        property.setDescription(propertyDto.getDescription());
    }
}
