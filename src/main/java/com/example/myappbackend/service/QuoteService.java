package com.example.myappbackend.service;

import com.example.myappbackend.model.Quote;
import com.example.myappbackend.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class QuoteService {

    private QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository){
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }

    public Quote save(Quote quote){
        return quoteRepository.save(quote);
    }

    public Quote find(Long id){
        return quoteRepository.findById(id).orElseThrow(()-> new NoSuchElementException("No person with id: " + id));
    }

    public void delete(Quote quote){
        quoteRepository.delete(quote);
    }

    public double calculateQuote(Quote quote){
        double totalQuote = 100*calculateVehicleTypeFactor(quote)*calculateEngineSizeFactor(quote)
                *calculateAdditionalDriversFactor(quote)
                *calculateCommercialUseFactor(quote)
                *calculateOutsideStateUseFactor(quote)
                *calculateVehicleValueFactor(quote);

        totalQuote = Math.round(totalQuote*100.0)/100.0;

        return totalQuote;
    }

    private double calculateVehicleTypeFactor(Quote quote){

        double vehicleTypeFactor = 0.0;

        String vehicleType = quote.getVehicleType().toLowerCase(Locale.ROOT);

        switch (vehicleType){
            case "cabriolet":
                vehicleTypeFactor = VehicleTypeFactor.CABRIOLET.getFactorValue();
                break;
            case "coupe":
                vehicleTypeFactor = VehicleTypeFactor.COUPE.getFactorValue();
                break;
            case "estate":
                vehicleTypeFactor = VehicleTypeFactor.ESTATE.getFactorValue();
                break;
            case "hatchback":
                vehicleTypeFactor = VehicleTypeFactor.HATCHBACK.getFactorValue();
                break;
            case "other":
                vehicleTypeFactor = VehicleTypeFactor.OTHER.getFactorValue();
                break;
        }

        return vehicleTypeFactor;
    }

    private double calculateEngineSizeFactor(Quote quote){
        String engineType = quote.getEngineSize().toLowerCase(Locale.ROOT);

        double engineTypeFactor = 0.0;

        switch (engineType){
            case "1000":
                engineTypeFactor = EngineSizeFactor.SIZE1000.getFactorValue();
                break;
            case "1600":
                engineTypeFactor = EngineSizeFactor.SIZE1600.getFactorValue();
                break;
            case "2000":
                engineTypeFactor = EngineSizeFactor.SIZE2000.getFactorValue();
                break;
            case "2500":
                engineTypeFactor = EngineSizeFactor.SIZE2500.getFactorValue();
                break;
            case "3000":
                engineTypeFactor = EngineSizeFactor.SIZE3000.getFactorValue();
                break;
            case "other":
                engineTypeFactor = EngineSizeFactor.OTHER.getFactorValue();
        }

        return engineTypeFactor;
    }

    private double calculateVehicleValueFactor(Quote quote){
        Double vehicleValue = quote.getCurrentValue();
        double vehicleValueFactor = 0.0;

        if(vehicleValue <= 5000){
            vehicleValueFactor = VehicleValueFactor.LESSTHAN5000OREQUALTO5000.getFactorValue();
        }
        else{
            vehicleValueFactor = VehicleValueFactor.GREATERTHAN5000.getFactorValue();
        }

        return vehicleValueFactor;
    }

    private double calculateCommercialUseFactor(Quote quote){
        Boolean commercialUseValue = quote.getCommercial();
        double commercialUseFactor = 0.0;

        if(commercialUseValue){
            commercialUseFactor = CommercialUseFactor.YES.getFactorValue();
        }
        else{
            commercialUseFactor = CommercialUseFactor.NO.getFactorValue();
        }

        return commercialUseFactor;
    }

    private double calculateOutsideStateUseFactor(Quote quote){
        Boolean outsideStateUseValue = quote.getRegisteredState();
        double outsideStateUseFactor = 0.0;

        if(outsideStateUseValue){
            outsideStateUseFactor = OutsideStateUseFactor.YES.getFactorValue();
        }
        else{
            outsideStateUseFactor = OutsideStateUseFactor.NO.getFactorValue();
        }

        return outsideStateUseFactor;
    }

    private double calculateAdditionalDriversFactor(Quote quote){
        String additionalDriversValue = quote.getAdditionalDrivers().toLowerCase(Locale.ROOT);

        double additionalDriversFactor = 0.0;


        if(Integer.parseInt(additionalDriversValue) < 2){
            additionalDriversFactor = AdditionalDriversFactor.LESSTHAN2.getFactorValue();
        }
        else{
            additionalDriversFactor = AdditionalDriversFactor.GREATERTHANOREQUALTO2.getFactorValue();
        }

        return additionalDriversFactor;
    }

}
