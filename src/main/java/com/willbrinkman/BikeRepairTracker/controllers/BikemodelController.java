package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.Bikemodel;
import com.willbrinkman.BikeRepairTracker.models.repositories.BikemodelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("bikemodels")
public class BikemodelController {

    @Autowired
    private BikemodelRepository bikemodelRepository;

    @GetMapping
    public String showAllBikemodels(Model model) {
        model.addAttribute("title", "All Bike Models");
        model.addAttribute("bikemodels", bikemodelRepository.findAll());
        return "bikemodels/index";
    }

    @GetMapping("new")
    public String showNewBikemodelForm(Model model) {
        model.addAttribute("title", "Add Bike Model");
        model.addAttribute(new Bikemodel());
        return "bikemodels/new";
    }

    @PostMapping("new")
    public String handleNewBikemodel( Errors errors, Model model,@ModelAttribute @Valid Bikemodel newBikemodel) {

        if (errors.hasErrors()) {
            return "bikemodel/new";
        }
        model.addAttribute(new Bikemodel());
        bikemodelRepository.save(newBikemodel);
        return "redirect:";
    }

    @GetMapping("view/{bikemodelId}")
    public String showViewBikemodel(Model model, @PathVariable int bikemodelId) {

        Optional optBikemodel = bikemodelRepository.findById(bikemodelId);
        if (optBikemodel.isPresent()) {
            Bikemodel bikemodel = (Bikemodel) optBikemodel.get();
            model.addAttribute("bikemodel", bikemodel);
            return "bikemodels/view";
        } else {
            return "redirect:../";
        }
    }
}
