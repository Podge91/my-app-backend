package com.example.myappbackend.controller;

import com.example.myappbackend.model.Quote;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenDBInitialisedWith1Record_whenGETQuotes_shouldreturn1item()throws Exception {

        final var mvcResult = mockMvc.perform(get("/quotes"))
                                                .andDo(print())
                                                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();

        List<Quote> quotesFromDB = objectMapper.readValue(contentAsString, new TypeReference<>() {});

        assertEquals(1, quotesFromDB.size());

    }

    @Test
    @DirtiesContext
    void GivenDBInitialisedWith1Record_whenPOSTQuotes_shouldSaveAndReturn() throws Exception {

        var prefix = "Mr.";
        var firstName = "John";
        var lastName = "Doe";
        var telephone = "12345678";
        var addressLine1 = "123";
        var addressLine2 = "Fake Street";
        var city = "Yemen";
        var vehicleType = "cabriolet";
        var engineSize = "1000";
        var additionalDrivers = "2";
        var commercial = "yes";
        var registeredState = "yes";
        var currentValue = "15000";
        var dateRegistered = "11-11-2021";

        Quote quote = new Quote(prefix,firstName,lastName,telephone,addressLine1,addressLine2,city,vehicleType,engineSize,additionalDrivers,commercial,registeredState,currentValue,dateRegistered);

        final var personAsJSON = objectMapper.writeValueAsString(quote);

        final var mvcResult = mockMvc
                .perform(post("/quotes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(personAsJSON))
                .andDo(print())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();

        Quote savedQuote = objectMapper.readValue(contentAsString, Quote.class);

        assertAll(
                () -> assertNotNull(savedQuote.getId()),
                () -> assertEquals(prefix,savedQuote.getPrefix()),
                () -> assertEquals(firstName,savedQuote.getFirstName()),
                () -> assertEquals(lastName,savedQuote.getLastName()),
                () -> assertEquals(addressLine1,savedQuote.getAddressLine1()),
                () -> assertEquals(addressLine2,savedQuote.getAddressLine2()),
                () -> assertEquals(city,savedQuote.getCity()),
                () -> assertEquals(vehicleType,savedQuote.getVehicleType()),
                () -> assertEquals(engineSize,savedQuote.getEngineSize()),
                () -> assertEquals(additionalDrivers,savedQuote.getAdditionalDrivers()),
                () -> assertEquals(commercial,savedQuote.getCommercial()),
                () -> assertEquals(registeredState,savedQuote.getRegisteredState()),
                () -> assertEquals(currentValue,savedQuote.getCurrentValue()),
                () -> assertEquals(dateRegistered,savedQuote.getDateRegistered())

        );

    }

}