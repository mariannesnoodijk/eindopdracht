package com.example.eindopdracht.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // This is to turn off the security
@ActiveProfiles("test")
class AccountServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc; // With this it uses the h2 database instead of the actual
}
