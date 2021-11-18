package com.example.myappbackend.controller;

import com.example.myappbackend.model.Quote;
import com.example.myappbackend.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuoteController {

    public static final String ID_NOT_FOUND_ERROR_MSG = "Quote not found, id:";

    private QuoteService quoteService;

    public QuoteController(QuoteService quoteService){
        this.quoteService = quoteService;
    }

    @GetMapping("/quotes")
    List<Quote> findAll(){
        return quoteService.findAll();
    }


    @PostMapping("/quotes")
    Quote save(@RequestBody Quote quote){
        return quoteService.save(quote);
    }

    @PutMapping("/quotes")
    Quote update(@RequestBody Quote quote){
        Quote quoteToUpdate;
        try{
            quoteToUpdate = quoteService.find(quote.getId());
        } catch(NoSuchElementException nse){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ID_NOT_FOUND_ERROR_MSG + quote.getId(), nse
            );
        }
        quoteToUpdate.setTelephone(quote.getTelephone());

        return quoteService.save(quoteToUpdate);
    }

    @DeleteMapping("/quotes/{id}")
    void delete(@PathVariable Long id){
        Quote quoteToDelete;

        try{
            quoteToDelete = quoteService.find(id);
        } catch (NoSuchElementException nse) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ID_NOT_FOUND_ERROR_MSG + id, nse);
        }
        quoteService.delete(quoteToDelete);
    }


}
