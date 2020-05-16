package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.Bikesize;
import com.willbrinkman.BikeRepairTracker.models.repositories.BikesizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("bikesizes")
public class BikesizeController {

    @Autowired
    private BikesizeRepository bikesizeRepository;

    @GetMapping
    public String showAllBikesizes(Model model) {
        model.addAttribute("title", "All Sizes");
        model.addAttribute("bikesizes", bikesizeRepository.findAll());
        return "bikesizes/index";
    }

    @GetMapping("new")
    public String showNewBikesizesForm(Model model) {
        model.addAttribute("title", "Add Sizes");
        model.addAttribute(new Bikesize());
        return "bikesizes/new";
    }

    @PostMapping("new")
    public String handleNewBikesize(@ModelAttribute @Valid Bikesize newBikesize,
                                             Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "bikesize/new";
        }
        model.addAttribute(new Bikesize());
        bikesizeRepository.save(newBikesize);
        return "redirect:";
    }

    @GetMapping("view/{bikesizeId}")
    public String showViewBikeSize(Model model, @PathVariable int bikesizeId) {

        Optional optionalBikesize = bikesizeRepository.findById(bikesizeId);
        if (optionalBikesize.isPresent()) {
            Bikesize bikesize = (Bikesize) optionalBikesize.get();
            model.addAttribute("bikesize", bikesize);
            return "bikesizes/view";
        } else {
            return "redirect:../";
        }
    }
}
