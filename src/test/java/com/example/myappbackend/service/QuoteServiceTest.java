package com.example.myappbackend.service;

import com.example.myappbackend.model.Quote;
import com.example.myappbackend.repository.QuoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;
    @InjectMocks
    private QuoteService quoteService;

    private String prefix = "Mr.";
    private String firstName = "John";
    private String lastName = "Doe";
    private String telephone = "12345678";
    private String addressLine1 = "123";
    private String addressLine2 = "Fake Street";
    private String city = "Yemen";
    private String vehicleType = "cabriolet";
    private String engineSize = "1000";
    private String additionalDrivers = "2";
    private Boolean commercial = true;
    private Boolean registeredState = true;
    private Double currentValue = 15000.00;
    private String dateRegistered = "11-11-2021";

    Quote quote = new Quote(2L,prefix,firstName,lastName,telephone,addressLine1,addressLine2,city,vehicleType,
            engineSize,additionalDrivers,commercial,registeredState,currentValue,dateRegistered);

    @Test
    void find(){
        when(quoteRepository.findById(1L)).thenReturn(Optional.of(quote));

        Quote quote = quoteService.find(1L);

        assertAll(
                () -> assertEquals(prefix,quote.getPrefix()),
                () -> assertEquals(firstName,quote.getFirstName()),
                () -> assertEquals(lastName,quote.getLastName()),
                () -> assertEquals(addressLine1,quote.getAddressLine1()),
                () -> assertEquals(addressLine2,quote.getAddressLine2()),
                () -> assertEquals(city,quote.getCity()),
                () -> assertEquals(vehicleType,quote.getVehicleType()),
                () -> assertEquals(engineSize,quote.getEngineSize()),
                () -> assertEquals(additionalDrivers,quote.getAdditionalDrivers()),
                () -> assertEquals(commercial,quote.getCommercial()),
                () -> assertEquals(registeredState,quote.getRegisteredState()),
                () -> assertEquals(currentValue,quote.getCurrentValue()),
                () -> assertEquals(dateRegistered,quote.getDateRegistered())
        );
    }
/**
    @Test
    void calculateQuote(){
        assertEquals(100,quoteService.calculateQuote(quote));
    }
 **/

}