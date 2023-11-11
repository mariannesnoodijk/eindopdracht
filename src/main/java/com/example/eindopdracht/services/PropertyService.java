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

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    private static void propertyToPropertyDto(Property p, PropertyDto pDto) {
        pDto.setAddress(p.getAddress());
        pDto.setPrice(p.getPrice());
        pDto.setDescription(p.getDescription());
    }

    private static void propertyDtoToProperty(PropertyDto propertyDto, Property property) {
        property.setAddress(propertyDto.getAddress());
        property.setPrice(propertyDto.getPrice());
        property.setDescription(propertyDto.getDescription());
    }

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

    public List<PropertyDto> getAllProperties() {

        List<Property> properties = propertyRepository.findAll();
        List<PropertyDto> propertyDtos = new ArrayList<>();

        for (Property p : properties) {
            PropertyDto pDto = new PropertyDto();
            propertyToPropertyDto(p, pDto);

            propertyDtos.add(pDto);
        }
        return propertyDtos;
    }

    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = new Property();
        propertyDtoToProperty(propertyDto, property);

        Property savedProperty = propertyRepository.save(property);

        PropertyDto savedPropertyDto = new PropertyDto();
        propertyToPropertyDto(savedProperty, savedPropertyDto);

        return savedPropertyDto;
    }

    public String deleteProperty(@RequestBody Long propertyId) {
        propertyRepository.deleteById(propertyId);
        return "Property successfully deleted";
    }
}
