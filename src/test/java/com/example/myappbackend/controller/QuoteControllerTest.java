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

import static com.example.myappbackend.controller.QuoteController.ID_NOT_FOUND_ERROR_MSG;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenDBInitialisedWith1Record_whenGETQuotes_shouldreturn1item()throws Exception {

        final var mvcResult = mockMvc.perform(get("/quotes/all/"))
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
        var commercial = true;
        var registeredState = true;
        var currentValue = 15000.00;
        var dateRegistered = "11-11-2021";

        Quote quote = new Quote(prefix,firstName,lastName,telephone,addressLine1,addressLine2,city,vehicleType,
                engineSize,additionalDrivers,commercial,registeredState,currentValue,dateRegistered);

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

    @Test
    @DirtiesContext
    void givenDBInitialisedWith1Record_whenDELETEQuoteWithExistingID_shouldReturn200() throws Exception {

        var existingID = 1;

        final var mvcResult = mockMvc
                .perform(delete("/quotes/" + existingID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DirtiesContext
    void givenDBInitialisedWith1Record_whenDELETEWithNonExistingID_shouldReturnErrorMsg() throws Exception{

        var nonExistingID = 10;

        final var mvcResult = mockMvc
                .perform(delete("/quotes/" + nonExistingID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        var expectedErrorMsg = ID_NOT_FOUND_ERROR_MSG + nonExistingID;

        assertEquals(expectedErrorMsg, mvcResult.getResponse().getErrorMessage());

    }

    @Test
    @DirtiesContext
    void givenDBInitialisedWith1Record_whenPUTQuoteWithExistingID_shouldReturnUpdatedQuote() throws Exception{

        var existingID = 1L;
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
        var commercial = true;
        var registeredState = true;
        var currentValue = 15000.00;
        var dateRegistered = "11-11-2021";

        Quote quote = new Quote(existingID,prefix,firstName,lastName,telephone,addressLine1,addressLine2,city,
                vehicleType,engineSize,additionalDrivers,commercial,registeredState,currentValue,dateRegistered);

        final var quoteAsJSON = objectMapper.writeValueAsString(quote);

        final var mvcResult = mockMvc
                .perform(put("/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quoteAsJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Quote updatedQuote = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Quote.class);

        assertAll(
                () -> assertEquals(existingID,updatedQuote.getId()),
                () -> assertEquals(prefix,updatedQuote.getPrefix()),
                () -> assertEquals(firstName,updatedQuote.getFirstName()),
                () -> assertEquals(lastName,updatedQuote.getLastName()),
                () -> assertEquals(addressLine1,updatedQuote.getAddressLine1()),
                () -> assertEquals(addressLine2,updatedQuote.getAddressLine2()),
                () -> assertEquals(city,updatedQuote.getCity()),
                () -> assertEquals(vehicleType,updatedQuote.getVehicleType()),
                () -> assertEquals(engineSize,updatedQuote.getEngineSize()),
                () -> assertEquals(additionalDrivers,updatedQuote.getAdditionalDrivers()),
                () -> assertEquals(commercial,updatedQuote.getCommercial()),
                () -> assertEquals(registeredState,updatedQuote.getRegisteredState()),
                () -> assertEquals(currentValue,updatedQuote.getCurrentValue()),
                () -> assertEquals(dateRegistered,updatedQuote.getDateRegistered())
        );

    }

    @Test
    @DirtiesContext
    void givenDBInitialisedWith1Record_whenPUTQuoteWithNonExistingID_shouldReturnErrorMSG() throws Exception{

        var nonExistingID = 10;
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
        var commercial = true;
        var registeredState = true;
        var currentValue = 15000.00;
        var dateRegistered = "11-11-2021";

        Quote quote = new Quote(nonExistingID,prefix,firstName,lastName,telephone,addressLine1,addressLine2,city,
                vehicleType,engineSize,additionalDrivers,commercial,registeredState,currentValue,dateRegistered);

        final var quoteAsJSON = objectMapper.writeValueAsString(quote);

        final var mvcResult = mockMvc
                .perform(put("/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(quoteAsJSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        var expectedErrorMSG = ID_NOT_FOUND_ERROR_MSG + nonExistingID;

        assertEquals(expectedErrorMSG, mvcResult.getResponse().getErrorMessage());

    }

}