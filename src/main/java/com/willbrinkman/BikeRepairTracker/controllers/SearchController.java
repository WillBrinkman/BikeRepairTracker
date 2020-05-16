package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.Bike;
import com.willbrinkman.BikeRepairTracker.models.BikeData;
import com.willbrinkman.BikeRepairTracker.models.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

import static com.willbrinkman.BikeRepairTracker.controllers.InventoryController.filterChoices;

@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private BikeRepository bikeRepository;



    @RequestMapping("")
    public String search(Model model){
        model.addAttribute("filters", filterChoices);
        return "search";
    }

    @PostMapping("results")
    public String SearchResults(@RequestParam String searchFilter, @RequestParam String searchQuery, Model model) {
        Iterable<Bike> bikes;
        if (searchQuery.toLowerCase().equals("all") || searchQuery.equals("")){
            bikes = bikeRepository.findAll();
        } else {
            bikes = BikeData.findFilterAndValue(searchFilter, searchQuery, bikeRepository.findAll());
        }

        model.addAttribute("filters", filterChoices);
        model.addAttribute("title", "Bikes registered with " + filterChoices.get(searchFilter) +" "+ searchQuery);
        model.addAttribute("bikes", bikes);

        return "search";
    }

}
