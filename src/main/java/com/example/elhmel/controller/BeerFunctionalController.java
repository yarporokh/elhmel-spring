package com.example.elhmel.controller;

import com.example.elhmel.model.Beer;
import com.example.elhmel.model.BeerDescription;
import com.example.elhmel.model.BeerType;
import com.example.elhmel.service.Impl.BeerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class BeerFunctionalController {
    private final BeerServiceImpl beerService;

    @Autowired
    public BeerFunctionalController(BeerServiceImpl beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/addbeer")
    public String getAddBeerPage(Model model) {
        List<BeerType> typeList = beerService.getALlBeerTypes();
        model.addAttribute("typeList", typeList);

        return "addbeer";
    }

    @PostMapping("/addbeer")
    public String addBeer(@RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("alcohol") Float alcohol,
                          @RequestParam("beerType") BeerType beerType
    ) {
        BeerDescription beerDescription = BeerDescription.builder()
                .description(description).build();

        Beer beer = Beer.builder()
                .name(name)
                .description(beerDescription)
                .alcohol(alcohol)
                .beerType(beerType).build();

        beerService.save(beer);

        return "redirect:/addbeer";
    }

    @PostMapping("/addbeertype")
    public String addBeerType(@RequestParam("newBeerTypeName") String newBeerTypeName) {
        beerService.saveNewBeerType(newBeerTypeName);
        return "redirect:/addbeer";
    }

    @PostMapping("/deletebeertype")
    public String deleteBeerType(@RequestParam("beerType") BeerType beerType) {
        beerService.deleteBeerType(beerType);
        return "redirect:/addbeer";
    }
}
