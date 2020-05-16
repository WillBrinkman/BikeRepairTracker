package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.*;
import com.willbrinkman.BikeRepairTracker.models.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.willbrinkman.BikeRepairTracker.models.Manufacturer;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

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

    @RequestMapping("/")
    public String index(Model model) {

        //model.addAttribute("title", "Bikes");

        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("repairForm")
    public String showBikeRepairForm(Model model) {
        model.addAttribute("title", "Add Bike");
        model.addAttribute(new Bike());
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("bikemodels", bikemodelRepository.findAll());
        model.addAttribute("bikesizes", bikesizeRepository.findAll());
        model.addAttribute("items", itemRepository.findAll());
        return "repairForm";
    }

    @PostMapping("repairForm")
    public String handleBikeRepairForm(@ModelAttribute @Valid Bike newBike, Errors errors, Model model, RedirectAttributes redirectAttrs, @RequestParam int manufacturerId, @RequestParam int bikemodelId, @RequestParam int bikesizeId, @RequestParam List<Integer> items) {



        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Bike");
            return "/repairForm";
        } else {
            Optional<Manufacturer> OptionalManufacturer = manufacturerRepository.findById(manufacturerId);
            Optional<Bikemodel> OptionalBikemodel = bikemodelRepository.findById(bikemodelId);
            Optional<Bikesize> OptionalBikesize = bikesizeRepository.findById(bikesizeId);

            if (OptionalManufacturer.isEmpty() || OptionalBikemodel.isEmpty() || OptionalBikesize.isEmpty()) {

                return "/repairForm";
            } else {

                Manufacturer manufacturer = OptionalManufacturer.get();
                Bikemodel bikemodel = OptionalBikemodel.get();
                Bikesize bikesize = OptionalBikesize.get();

                newBike.setManufacturer(manufacturer);
                newBike.setBikemodel(bikemodel);
                newBike.setBikesize(bikesize);

                List<Item> itemObjects = (List<Item>) itemRepository.findAllById(items);
                newBike.setItems(itemObjects);
                bikeRepository.save(newBike);
                model.addAttribute("manufacturers", manufacturer);
                model.addAttribute("bikemodels", bikemodel);
                model.addAttribute("bikesizes", bikesize);

            }
            model.addAttribute(  "message","Bike has been created!");
            redirectAttrs.addAttribute("newBikeId", newBike.getId());
            return "redirect:/view/{newBikeId}";
        }
    }

    @GetMapping("view/{bikeId}")
    public String showViewBike(Model model, @PathVariable int bikeId) {
        Optional optionalBike = bikeRepository.findById(bikeId);
        if (optionalBike.isPresent()) {
            Bike bike = (Bike) optionalBike.get();

            model.addAttribute("bike", bike);
            model.addAttribute("message", model.getAttribute("message"));
            return "view";
        }
        return "view";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bike Id:" + id));
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("bikemodels", bikemodelRepository.findAll());
        model.addAttribute("bikesizes", bikesizeRepository.findAll());
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("bike", bike);
        return "update-bike";
    }

//    @PostMapping("update/{id}")
//    public String handleUpdateBike(@PathVariable("id") int bikeId, @Valid Bike bike, Errors errors, Model model){
//        Optional optionalBike = bikeRepository.findById(bikeId);
//        if (optionalBike.isPresent()) {
//            bike = (Bike) optionalBike.get();
//            bikeRepository.delete(bike);
//            model.addAttribute("bikes", bikeRepository.findAll());
//            return "update-bike";
//        }
//        return "update-bike";
//    }

    @PostMapping("/update/{id}")
    public String updateBike(@PathVariable("id") long id,Bike newBike, @Valid Bike bike,
                             Errors errors, Model model,  RedirectAttributes redirectAttrs, @RequestParam int manufacturerId, @RequestParam int bikemodelId, @RequestParam int bikesizeId, @RequestParam List<Integer> items) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Bike");
            return "/update-bike";
        } else {
            Optional<Manufacturer> OptionalManufacturer = manufacturerRepository.findById(manufacturerId);
            Optional<Bikemodel> OptionalBikemodel = bikemodelRepository.findById(bikemodelId);
            Optional<Bikesize> OptionalBikesize = bikesizeRepository.findById(bikesizeId);

            if (OptionalManufacturer.isEmpty() || OptionalBikemodel.isEmpty() || OptionalBikesize.isEmpty()) {

                return "/update-bike";
            } else {

                Manufacturer manufacturer = OptionalManufacturer.get();
                Bikemodel bikemodel = OptionalBikemodel.get();
                Bikesize bikesize = OptionalBikesize.get();

                newBike.setManufacturer(manufacturer);
                newBike.setBikemodel(bikemodel);
                newBike.setBikesize(bikesize);

                List<Item> itemObjects = (List<Item>) itemRepository.findAllById(items);
                newBike.setItems(itemObjects);
                bikeRepository.save(newBike);
                model.addAttribute("manufacturers", manufacturer);
                model.addAttribute("bikemodels", bikemodel);
                model.addAttribute("bikesizes", bikesize);

            }
            model.addAttribute(  "message","Bike has been created!");
            redirectAttrs.addAttribute("newBikeId", newBike.getId());
            return "redirect:/view/{newBikeId}";
        }
    }

    @GetMapping("delete/{id}")
    public String handleDeleteBike(@PathVariable("id") int id, Model model){
        Optional optionalBike = bikeRepository.findById(id);


        if (optionalBike.isPresent()) {

            Bike bike = (Bike) optionalBike.get();
            bikeRepository.delete(bike);
            model.addAttribute("bikes", bikeRepository.findAll());
            model.addAttribute("message", "Successfully deleted Bike "+ id);
            return "index";
        }
        return "index";
    }


//    @GetMapping("/user/registration")
//    public String showRegistrationForm(WebRequest request, Model model) {
//        model.addAttribute("user", userDto);
//        return "registration";
//    }
//
//    @PostMapping("/user/registration")
//    public ModelAndView registerUserAccount
//            (@ModelAttribute("user") @Valid UserDto userDto,
//             HttpServletRequest request, Errors errors) {
//
//        try {
//            User registered = userService.registerNewUserAccount(userDto);
//        } catch (UserAlreadyExistException uaeEx) {
//            mav.addObject("message", "An account for that username/email already exists.");
//            return mav;
//        }
//        return new ModelAndView("successRegister", "user", userDto);
//    }
}
