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
    private String additionalDrivers = "0";
    private Boolean commercial = false;
    private Boolean registeredState = false;
    private Double currentValue = 5000.00;
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

    @Test
    void save(){
        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote saveQuote = quoteService.save(quote);

        assertEquals(quote,saveQuote);
    }

    @Test
    void calculateQuote(){
        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(quote.getQuoteAmount(),calculatedQuote.getQuoteAmount());
    }

    @Test
    void testQuoteAmount1(){

        Double testFactor1Result = 371.71;

        quote.setVehicleType("hatchback");
        quote.setEngineSize("1600");
        quote.setAdditionalDrivers("3");
        quote.setCommercial(true);
        quote.setRegisteredState(true);
        quote.setCurrentValue(5000.00);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactor1Result,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testQuoteAmount2(){
        Double testFactor2Result = 514.80;

        quote.setVehicleType("cabriolet");
        quote.setEngineSize("3000");
        quote.setAdditionalDrivers("1");
        quote.setCommercial(false);
        quote.setRegisteredState(false);
        quote.setCurrentValue(15000.00);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactor2Result,calculatedQuote.getQuoteAmount());

    }

    @Test
    void testFactorCabriolet(){
        Double testFactorResult = 143.00;

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorCoupe(){
        Double testFactorResult = 154.00;

        quote.setVehicleType("coupe");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorEstate(){
        Double testFactorResult = 165.00;

        quote.setVehicleType("estate");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorHatchback(){
        Double testFactorResult = 176.00;

        quote.setVehicleType("hatchback");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorVehicleTypeOther(){
        Double testFactorResult = 187.00;

        quote.setVehicleType("Other");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactor1000(){
        Double testFactorResult = 143.00;

        quote.setEngineSize("1000");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactor1600(){
        Double testFactorResult = 228.80;

        quote.setEngineSize("1600");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactor2000(){
        Double testFactorResult = 286.00;

        quote.setEngineSize("2000");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactor2500(){
        Double testFactorResult = 357.50;

        quote.setEngineSize("2500");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactor3000(){
        Double testFactorResult = 429.00;

        quote.setEngineSize("3000");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorEngineSizeOther(){
        Double testFactorResult = 500.50;

        quote.setEngineSize("other");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorVehicleValueLessThan5000(){
        Double testFactorResult = 143.00;

        quote.setCurrentValue(4999.99);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorVehicleValue5000(){
        Double testFactorResult = 143.00;

        quote.setCurrentValue(5000.00);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorVehicleValueGreaterThan5000(){
        Double testFactorResult = 171.60;

        quote.setCurrentValue(5000.01);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorAdditionalDriversLessThan2(){
        Double testFactorResult = 143.00;

        quote.setAdditionalDrivers("1");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorAdditionalDrivers2(){
        Double testFactorResult = 156.00;

        quote.setAdditionalDrivers("2");

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorCommercialTrue(){
        Double testFactorResult = 157.30;

        quote.setCommercial(true);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorRegisteredStateTrue(){
        Double testFactorResult = 157.30;

        quote.setRegisteredState(true);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorCommercialFalse(){
        Double testFactorResult = 143.00;

        quote.setCommercial(false);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }

    @Test
    void testFactorRegisteredStateFalse(){
        Double testFactorResult = 143.00;

        quote.setRegisteredState(false);

        when(quoteRepository.save(quote)).thenReturn(quote);

        Quote calculatedQuote = quoteService.save(quote);

        assertEquals(testFactorResult,calculatedQuote.getQuoteAmount());
    }



}