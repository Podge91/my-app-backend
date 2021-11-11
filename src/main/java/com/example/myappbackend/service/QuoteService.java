package com.example.myappbackend.service;

import com.example.myappbackend.model.Quote;
import com.example.myappbackend.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
