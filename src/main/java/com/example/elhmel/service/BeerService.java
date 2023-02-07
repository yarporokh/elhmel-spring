package com.example.elhmel.service;

import com.example.elhmel.model.Beer;
import com.example.elhmel.model.BeerType;

import java.util.List;

public interface BeerService {
    List<Beer> getAll();

    void save(Beer beer);

    Beer getBeerById(Long id);

    List<BeerType> getALlBeerTypes();

    void saveNewBeerType(String name);

    void deleteBeerType(BeerType beerType);
    void deleteBeer(Beer beer);
}
