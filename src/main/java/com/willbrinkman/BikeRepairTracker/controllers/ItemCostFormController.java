package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.*;
import com.willbrinkman.BikeRepairTracker.models.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "itemCostForm")
public class ItemCostFormController {

    @Autowired
    private ItemRepository itemRepository;


//    public Double CostFormController(Item item, double theItemAndRepairCost) {
//        item.setItemAndRepairCost(theItemAndRepairCost);
//        return item.getItemAndRepairCost();
//    }

   // @Secured({ "ROLE_ADMIN" })
    @GetMapping("/itemCostForm/{id}")
    public String showUpdateItemForm(@PathVariable("id") int id, Model model) {
                Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        model.addAttribute("title", "Update Item");
        model.addAttribute(new Item());
        return "items/new";
    }

//    @Secured({ "ROLE_ADMIN" })
    @PostMapping("/updateItemCost/{id}")
    public String handleUpdateItemForm(@PathVariable("id") int id,@ModelAttribute @Valid Item updatedItem,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "update-item";
        }

        itemRepository.save(updatedItem);
        return "redirect:";
    }

//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        Bike bike = bikeRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid bike Id:" + id));
//        model.addAttribute("manufacturers", manufacturerRepository.findAll());
//        model.addAttribute("bikemodels", bikemodelRepository.findAll());
//        model.addAttribute("bikesizes", bikesizeRepository.findAll());
//        model.addAttribute("items", itemRepository.findAll());
//        model.addAttribute("bike", bike);
//        return "update-bike";
//    }
////
//    @Secured({ "ROLE_ADMIN" })
//    @GetMapping("/itemCostForm/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        Item item = itemRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
//        model.addAttribute("items", itemRepository.findAll());
//        model.addAttribute("item", item);
//        return "update-item";
//    }
//
//    @Secured({ "ROLE_ADMIN" })
//    @PostMapping("/updateItemCost/{id}")
//    public String updateItem(@PathVariable("id") long id, Item newItem, @Valid Item item,
//                             Errors errors, Model model, RedirectAttributes redirectAttrs, @RequestParam int itemId) {
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Item");
//            return "/update-item";
//        } else {
//
//                itemRepository.save(newItem);
//
//            }
//            model.addAttribute(  "message","Bike has been created!");
//            redirectAttrs.addAttribute("newItemId", newItem.getId());
//            return "redirect:/items/view/{newItemId}";
//        }
//    }

    DecimalFormat money = new DecimalFormat("$#,##0.00");
}
