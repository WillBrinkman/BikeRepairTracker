package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.*;
import com.willbrinkman.BikeRepairTracker.models.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private ItemRepository itemRepository;
//
////
//    public Double CostFormController(Item item, double theItemAndRepairCost) {
//        item.setItemAndRepairCost(theItemAndRepairCost);
//        return item.getItemAndRepairCost();
//    }
//
//    @GetMapping("/itemCostForm/{id}")
//    public String showUpdateForm(@PathVariable("id") int id, Model model) {
//        Item item = itemRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
//        model.addAttribute("items", itemRepository.findAll());
//        model.addAttribute("item", item);
//        return "update-item";
//    }
//
//    @PostMapping("/updateItemCost/{id}")
//    public String updateItem(@PathVariable("id") long id, Item newItem, @Valid Item item,
//                             Errors errors, Model model, RedirectAttributes redirectAttrs, @RequestParam int itemId) {
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Item");
//            return "/update-item";
//        } else {
//
//
//                newItem.setItemAndRepairCost();
//
//
//                List<Item> itemObjects = (List<Item>) itemRepository.findAllById(items);
//                newItem.setItemAndRepairCost(itemObjects);
//                itemRepository.save(newItem);
//                model.addAttribute("itemAndRepairCost", manufacturer);
//
//            }
//            model.addAttribute(  "message","Bike has been created!");
//            redirectAttrs.addAttribute("newBikeId", newBike.getId());
//            return "redirect:/view/{newBikeId}";
//        }
//    }

    DecimalFormat money = new DecimalFormat("$#,##0.00");
}
