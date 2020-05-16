package com.willbrinkman.BikeRepairTracker.controllers;


import com.willbrinkman.BikeRepairTracker.models.Bike;
import com.willbrinkman.BikeRepairTracker.models.BikeData;
import com.willbrinkman.BikeRepairTracker.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;


@Controller
@RequestMapping(value = "inventory")
public class InventoryController {

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private BikemodelRepository bikemodelRepository;

    @Autowired
    private BikesizeRepository bikesizeRepository;

    @Autowired
    private ItemRepository itemRepository;

    static HashMap<String, String> filterChoices = new HashMap<>();

    public InventoryController() {
        filterChoices.put("all", "");
        filterChoices.put("id", "Id");
        filterChoices.put("manufacturer", "Manufacturer");
        filterChoices.put("bikemodel", "Bike Model");
        filterChoices.put("bikesize", "Bike Size");
    }

    @RequestMapping("")
    public String Inventory(Model model) {
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("bikemodels", bikemodelRepository.findAll());
        model.addAttribute("bikesizes", bikesizeRepository.findAll());

        return "inventory";
    }

    @RequestMapping(value = "bikes")
    public String showBikesFilterAndValue(Model model, @RequestParam String filter, @RequestParam String value) {
        Iterable<Bike> bikes;
        if (filter.toLowerCase().equals("all")){
            bikes = bikeRepository.findAll();
            model.addAttribute("title", "All Bikes");
        } else {
            bikes = BikeData.findFilterAndValue(filter, value, bikeRepository.findAll());
            model.addAttribute("title", "Bikes with " + filterChoices.get(filter) + " " + value);
        }
        model.addAttribute("bikes", bikes);

        return "bikes-inventory";
    }
}
