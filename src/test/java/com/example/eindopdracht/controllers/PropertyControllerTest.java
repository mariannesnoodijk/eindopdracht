//package com.example.eindopdracht.controllers;
//
//
//import com.example.eindopdracht.dto.PropertyDto;
//import com.example.eindopdracht.dto.ViewingDto;
//import com.example.eindopdracht.models.Property;
//import com.example.eindopdracht.models.Viewing;
//import com.example.eindopdracht.repositories.PropertyRepository;
//import com.example.eindopdracht.repositories.ViewingRepository;
//import com.example.eindopdracht.security.JwtService;
//import com.example.eindopdracht.services.PropertyService;
//import com.example.eindopdracht.services.ViewingService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.hamcrest.Matchers.is;
//
//@WebMvcTest(ViewingController.class) // This is the link with the ViewingController class
//@AutoConfigureMockMvc(addFilters = false) // Use this to bypass security settings.
//@ActiveProfiles("test")
//public class PropertyControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    PropertyService propertyService;
//
//    @MockBean
//    PropertyRepository propertyRepository;
//
//    @MockBean
//    JwtService jwtService;
//
//    @Test
//    void testShouldCreateProperty() throws Exception {
//        Property newProperty = new Property();
//        newProperty.setAddress("Test Winkelstraat 10");
//        newProperty.setPrice(525000.0);
//        newProperty.setDescription("Aliquam eu lorem libero. In pharetra blandit ligula, non volutpat quam hendrerit non. Nulla et diam lorem.");
//
//        propertyRepository.save(newProperty);
//
//        PropertyDto pDto = new PropertyDto();
//        pDto.setAddress(newProperty.getAddress());
//        pDto.setPrice(newProperty.getPrice());
//        pDto.setDescription(newProperty.getDescription());
//
//        Mockito.when(propertyService.createProperty(Mockito.any(PropertyDto.class))).thenReturn(pDto);
//
//        this.mockMvc
//                .perform(MockMvcRequestBuilders.post("/properties").content("{\n" +
//                        "    \"address\" : \"Test Winkelstraat 10\",\n" +
//                        "    \"price\" : \"525000.0\", \n" +
//                        "    \"description\" : \"Aliquam eu lorem libero. In pharetra blandit ligula, non volutpat quam hendrerit non. Nulla et diam lorem.\" \n" +
//                        "}").contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.address", is("Test Winkelstraat 10")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is("525000.0")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description", is("Aliquam eu lorem libero. In pharetra blandit ligula, non volutpat quam hendrerit non. Nulla et diam lorem.")));
//
//        Mockito.verify(propertyService, Mockito.times(1)).createProperty(Mockito.any(PropertyDto.class));
//    }
//}
