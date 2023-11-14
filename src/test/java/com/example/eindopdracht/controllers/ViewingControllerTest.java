package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.ViewingRepository;
import com.example.eindopdracht.security.JwtService;
import com.example.eindopdracht.services.ViewingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultHandlersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ViewingController.class) // This is the link with the ViewingController class
@AutoConfigureMockMvc(addFilters = false) // Use this to bypass security settings.
@ActiveProfiles("test")
class ViewingControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ViewingService viewingService;

    @MockBean
    ViewingRepository viewingRepository;

    @MockBean
    JwtService jwtService;

    @Test
    void testShouldGetAllViewings() throws Exception {

        ViewingDto vDto = new ViewingDto();
        vDto.setFullname("Jan Jansen");
        vDto.setPhonenumber("0611122333");
        vDto.setEmailaddress("janjansen@test.com");
//        vDto.setViewingdate(20/12/2023);
//        vDto.setViewingtime("");

        ViewingDto vDto2 = new ViewingDto();
        vDto2.setFullname("Pietje Puk");
        vDto2.setPhonenumber("0611100222");
        vDto2.setEmailaddress("pietjepuk@test.com");
//        vDto.setViewingdate(20/12/2023);
//        vDto.setViewingtime("");

        List<ViewingDto> viewingDtoList = new ArrayList<>();
        viewingDtoList.add(vDto);
        viewingDtoList.add(vDto2);

        Mockito.when(viewingService.getAllViewings()).thenReturn(viewingDtoList);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/viewings"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullname", is("Jan Jansen")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phonenumber", is("0611122333")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].emailaddress", is("janjansen@test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].fullname", is("Pietje Puk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phonenumber", is("0611100222")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].emailaddress", is("pietjepuk@test.com")));

        // Verify that the getAllViewings method is called
        Mockito.verify(viewingService, Mockito.times(1)).getAllViewings();
    }

    @Test
    void testShouldCreateViewing() throws Exception {
        Viewing newViewing = new Viewing();
        newViewing.setFullname("Marianne test");
        newViewing.setPhonenumber("0612308024");
        newViewing.setEmailaddress("test@test.com");

        Account account1 = new Account();
        account1.setFirstname("Jan");
        account1.setLastname("Jansen");
        account1.setEmailaddress("jan@jansen.com");

        viewingRepository.save(newViewing);

        ViewingDto vDto = new ViewingDto();
        vDto.setFullname(newViewing.getFullname());
        vDto.setPhonenumber(newViewing.getPhonenumber());
        vDto.setEmailaddress(newViewing.getEmailaddress());

        Mockito.when(viewingService.createViewing(Mockito.any(ViewingDto.class))).thenReturn(vDto);

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/viewings").content("{\n" +
                        "    \"fullname\" : \"Marianne test\",\n" +
                        "    \"phonenumber\" : \"0612308024\", \n" +
                        "    \"emailaddress\" : \"test@test.com\" \n" +
                        "}").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullname", is("Marianne test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phonenumber", is("0612308024")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailaddress", is("test@test.com")));

        Mockito.verify(viewingService, Mockito.times(1)).createViewing(Mockito.any(ViewingDto.class));
    }

    @Test
    void testShouldDeleteViewing() throws Exception {
        // Create a ViewingDto for testing
        ViewingDto vDto = new ViewingDto();
        vDto.setFullname("John Doe");
        vDto.setPhonenumber("0612345678");
        vDto.setEmailaddress("john.doe@example.com");

        // Mock the deleteViewing method
        Mockito.doNothing().when(viewingService).deleteViewing(Mockito.anyLong());

        // Perform the delete request
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/viewings/{viewingId}", 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // Verify that the deleteViewing method is called with the correct argument
        Mockito.verify(viewingService, Mockito.times(1)).deleteViewing(1L);
    }
}
