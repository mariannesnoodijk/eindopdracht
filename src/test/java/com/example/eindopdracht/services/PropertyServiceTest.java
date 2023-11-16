package com.example.eindopdracht.services;

import com.example.eindopdracht.dto.PropertyDto;
import com.example.eindopdracht.exceptions.IdNotFoundException;
import com.example.eindopdracht.models.Property;
import com.example.eindopdracht.repositories.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// Import necessary classes and annotations for testing with Mockito and JUnit
@ExtendWith(MockitoExtension.class)
class PropertyServiceTest {

    // Mocking the dependencies needed for testing
    @Mock
    private PropertyRepository propertyRepository;

    // Injecting the mocks into the service being tested
    @InjectMocks
    private PropertyService propertyService;

    // Test method for creating a new property
    @Test
    void testShouldCreateProperty() {
        // Arrange - setting up the test data for a new property
        Property newProperty = new Property();
        newProperty.setAddress("Test Hoofdstraat 1");
        newProperty.setPrice(200000.0);
        newProperty.setDescription("This is a test description for test createProperty");

        // Mocking the behavior of the propertyRepository
        Mockito.when(propertyRepository.save(Mockito.any(Property.class))).thenReturn(newProperty);

        // Act - calling the method to be tested
        PropertyDto savedPropertyDto = propertyService.createProperty(new PropertyDto());

        // Assert - checking the result
        assertEquals("Test Hoofdstraat 1", savedPropertyDto.getAddress());
        assertEquals(200000.0, savedPropertyDto.getPrice());
        assertEquals("This is a test description for test createProperty", savedPropertyDto.getDescription());
    }

    // Test method for getting a single property
    @Test
    void testShouldGetProperty() {
        // Arrange - creating/adding a new property
        Long propertyId = 1L;
        Property property = new Property();
        property.setAddress("Teststraat 123");
        property.setPrice(100000.0);
        property.setDescription("This is a test description for test getProperties");

        // Mocking the behavior of the propertyRepository
        Mockito.when(propertyRepository.findById(propertyId)).thenReturn(Optional.of(property));

        // Act - calling the method to be tested
        PropertyDto propertyDto = propertyService.getProperty(propertyId);

        // Assert - checking the result
        assertEquals("Teststraat 123", propertyDto.getAddress());
        assertEquals(100000.0, propertyDto.getPrice());
        assertEquals("This is a test description for test getProperties", propertyDto.getDescription());
    }

    // Test method for getting a property that does not exist
    @Test
    void testShouldGetPropertyNotFound() {
        // Arrange - creating/adding a non-existing property
        Long nonExistingPropertyId = 2L;
        // Mocking the behavior of the propertyRepository
        Mockito.when(propertyRepository.findById(nonExistingPropertyId)).thenReturn(Optional.empty());

        // Act and Assert - checking for an exception
        assertThrows(IdNotFoundException.class, () -> propertyService.getProperty(nonExistingPropertyId));
    }

    // Test method for getting all properties
    @Test
    void testShouldGetAllProperties() {
        // Arrange - setting up the test data with multiple properties
        Property property1 = new Property();
        property1.setAddress("Test Hoofdstraat 1");
        property1.setPrice(200000.0);
        property1.setDescription("This is a test description for test getAllProperties");

        Property property2 = new Property();
        property2.setAddress("Test Hoofdstraat 2");
        property2.setPrice(500000.0);
        property2.setDescription("This is another test description for test getAllProperties");

        List<Property> properties = new ArrayList<>();
        properties.add(property1);
        properties.add(property2);

        // Mocking the behavior of the propertyRepository
        Mockito.when(propertyRepository.findAll()).thenReturn(properties);

        // Act - calling the method to be tested
        List<PropertyDto> result = propertyService.getAllProperties();

        // Assert - checking the result
        assertEquals(2, result.size());
    }

    // Test method for deleting a property
    @Test
    void testShouldDeleteProperty() {
        // Arrange - setting up the test data for a property to be deleted
        Long propertyId = 1L;

        // Mocking the behavior of the propertyRepository
        Mockito.doNothing().when(propertyRepository).deleteById(propertyId);

        // Act - calling the method to be tested
        String result = propertyService.deleteProperty(propertyId);

        // Assert - verifying the interactions and checking the result
        // Verify that the deleteById method of propertyRepository is called with the correct ID
        Mockito.verify(propertyRepository).deleteById(propertyId);
        // Verify the returned message
        assertEquals("Property successfully deleted", result);
    }
}