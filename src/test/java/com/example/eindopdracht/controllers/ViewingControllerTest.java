package com.example.eindopdracht.controllers;

import com.example.eindopdracht.dto.ViewingDto;
import com.example.eindopdracht.models.Account;
import com.example.eindopdracht.models.Viewing;
import com.example.eindopdracht.repositories.AccountRepository;
import com.example.eindopdracht.repositories.ViewingRepository;
import com.example.eindopdracht.security.JwtService;
import com.example.eindopdracht.services.ViewingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // Use this to bypass security settings.
@ActiveProfiles("test")
class ViewingControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ViewingService viewingService;

    @Autowired
    ViewingRepository viewingRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtService jwtService;

    // Test to create a new viewing
    @Test
    void testShouldCreateViewing() throws Exception {

        // Create an account for testing
        Account account1 = new Account();
        account1.setAccountId(123L);
        account1.setFirstname("Jan");
        account1.setLastname("Jansen");
        account1.setEmail("jan@jansen.com");

        account1.setAccountId(
                accountRepository.save(account1).getAccountId());

        // Create a viewing for testing
        Viewing newViewing = new Viewing();
        newViewing.setFullname("Marianne test");
        newViewing.setPhonenumber("0612308024");
        newViewing.setEmail("test@test.com");
        newViewing.setDate(LocalDate.of(2024, 2, 9));
        newViewing.setAccount(account1);

        viewingRepository.save(newViewing);

        // Create a ViewingDto for testing
        ViewingDto vDto = new ViewingDto();
        vDto.setFullname(newViewing.getFullname());
        vDto.setPhonenumber(newViewing.getPhonenumber());
        vDto.setEmail(newViewing.getEmail());
        vDto.setAccountId(newViewing.getAccount().getAccountId());
        vDto.setDate(newViewing.getDate());
        vDto.setTime(newViewing.getTime());

        // Perform the POST request and assert the response
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/viewings").content(
                        "{\n" +
                                "    \"fullname\" : \"Marianne test\",\n" +
                                "    \"phonenumber\" : \"0612308024\", \n" +
                                "    \"email\" : \"test@test.com\", \n" +
                                "    \"date\" : \"2024-02-09\", \n" +
                                "    \"time\" : \"11:29\", \n" +
                                "    \"accountId\" : \""+ account1.getAccountId()+"\" \n" +
                                "}"
                ).contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullname") .value("Marianne test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phonenumber") .value("0612308024"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email") .value("test@test.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date") .value("2024-02-09"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.time") .value("11:29:00"));
    }

    // Test to retrieve all viewings
    @Test
    void testShouldGetAllViewings() throws Exception {

        // Create an account for testing
        Account account1 = new Account();
        account1.setAccountId(123L);
        account1.setFirstname("Jan");
        account1.setLastname("Jansen");
        account1.setEmail("jan@jansen.com");

        accountRepository.save(account1);

        // Create a viewing for testing
        Viewing newViewing = new Viewing();
        newViewing.setFullname("Jan Jansen");
        newViewing.setPhonenumber("0611122333");
        newViewing.setEmail("janjansen@test.com");
        newViewing.setDate(LocalDate.of(2024, 2, 9));
        newViewing.setTime(LocalTime.of(11, 30, 00));
        newViewing.setAccount(account1);

        viewingRepository.save(newViewing);

        // Perform the GET request and assert the response
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/viewings"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fullname", is("Jan Jansen")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phonenumber", is("0611122333")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", is("janjansen@test.com")));
    }

    // Test to delete a viewing
    @Test
    void testShouldDeleteViewing() throws Exception {
        // Create a ViewingDto for testing
        ViewingDto vDto = new ViewingDto();
        vDto.setFullname("John Doe");
        vDto.setPhonenumber("0612345678");
        vDto.setEmail("john.doe@example.com");

        // Perform the DELETE request and assert the response
        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/viewings/{viewingId}", 1L))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
