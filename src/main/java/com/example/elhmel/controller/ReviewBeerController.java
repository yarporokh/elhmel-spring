package com.example.elhmel.controller;

import com.example.elhmel.model.Beer;
import com.example.elhmel.service.Impl.BeerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewBeerController {
    private final BeerServiceImpl beerService;

    @Autowired
    public ReviewBeerController(BeerServiceImpl beerService) {
        this.beerService = beerService;
    }


    @GetMapping("/beerlist")
    public String getBeerListPage(Model model) {
        model.addAttribute("beerList", beerService.getAll());
        return "beerlist";
    }

    @PostMapping("/deletebeer")
    public String deleteBeer(@RequestParam Beer beer) {
        beerService.deleteBeer(beer);
        return "redirect: /beerlist";
    }
}
