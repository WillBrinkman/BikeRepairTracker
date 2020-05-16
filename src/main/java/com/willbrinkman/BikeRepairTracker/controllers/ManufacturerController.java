package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.Manufacturer;
import com.willbrinkman.BikeRepairTracker.models.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping
    public String showAllManufacturers(Model model) {
        model.addAttribute("title", "All Manufacturers");
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "manufacturers/index";
    }

    @GetMapping("new")
    public String showNewEmployerForm(Model model) {
        model.addAttribute("title", "Add Manufacturer");
        model.addAttribute(new Manufacturer());
        return "manufacturers/new";
    }

    @PostMapping("new")
    public String processNewManufacturerForm(@ModelAttribute @Valid Manufacturer newManufacturer,
                                             Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "manufacturer/new";
        }
        model.addAttribute(new Manufacturer());
        manufacturerRepository.save(newManufacturer);
        return "redirect:";
    }

    @GetMapping("view/{manufacturerId}")
    public String showViewManufacturer(Model model, @PathVariable int manufacturerId) {

        Optional optionalManufacturer = manufacturerRepository.findById(manufacturerId);
        if (optionalManufacturer.isPresent()) {
            Manufacturer manufacturer = (Manufacturer) optionalManufacturer.get();
            model.addAttribute("manufacturer", manufacturer);
            return "manufacturers/view";
        } else {
            return "redirect:../";
        }
    }
}
