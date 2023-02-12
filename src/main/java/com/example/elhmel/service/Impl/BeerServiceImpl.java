package com.example.elhmel.service.Impl;

import com.example.elhmel.model.Beer;
import com.example.elhmel.model.BeerType;
import com.example.elhmel.repository.BeerRepository;
import com.example.elhmel.repository.BeerTypeRepository;
import com.example.elhmel.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerTypeRepository beerTypeRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository, BeerTypeRepository beerTypeRepository) {
        this.beerRepository = beerRepository;
        this.beerTypeRepository = beerTypeRepository;
    }

    @Override
    @Transactional
    public List<Beer> getAll() {
        return beerRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Beer beer) {
        beerRepository.save(beer);
    }

    @Override
    @Transactional
    public Beer getBeerById(Long id) {
        return beerRepository.getById(id);
    }

    @Override
    @Transactional
    public List<BeerType> getALlBeerTypes() {
        return beerTypeRepository.findAll();
    }

    @Override
    @Transactional
    public void saveNewBeerType(String name) {
        BeerType beerType = BeerType.builder()
                .type(name)
                .build();
        beerTypeRepository.save(beerType);
    }

    @Override
    @Transactional
    public void deleteBeerType(BeerType beerType) {
        beerTypeRepository.delete(beerType);
    }

    @Override
    @Transactional
    public void deleteBeer(Beer beer) {
        beerRepository.delete(beer);
    }
}
