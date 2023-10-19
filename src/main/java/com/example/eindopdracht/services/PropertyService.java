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
        pDto.setId(p.getId());
        pDto.setStreetName(p.getStreetName());
        pDto.setHouseNumber(p.getHouseNumber());
        pDto.setNumberOfRooms(p.getNumberOfRooms());
        pDto.setSurface(p.getSurface());
        pDto.setPrice(p.getPrice());
        pDto.setAvailable(p.getAvailable());
        pDto.setMatch(p.getMatch());
    }

    private static void propertyDtoToProperty(PropertyDto propertyDto, Property property) {
        property.setId(propertyDto.getId());
        property.setStreetName(propertyDto.getStreetName());
        property.setHouseNumber(propertyDto.getHouseNumber());
        property.setNumberOfRooms(propertyDto.getNumberOfRooms());
        property.setSurface(propertyDto.getSurface());
        property.setPrice(propertyDto.getPrice());
        property.setAvailable(propertyDto.getAvailable());
        property.setMatch(propertyDto.getMatch());
    }

    public PropertyDto getProperty(Long id) {
        Optional<Property> property = propertyRepository.findById(id);
        if (property.isPresent()) {
            Property p = property.get();
            PropertyDto pDto = new PropertyDto();
            propertyToPropertyDto(p, pDto);
            return (pDto);
        } else {
            throw new IdNotFoundException("Property not found with ID: " + id);
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

    public void deleteProperty(@RequestBody Long id) {
        propertyRepository.deleteById(id);
    }
}
