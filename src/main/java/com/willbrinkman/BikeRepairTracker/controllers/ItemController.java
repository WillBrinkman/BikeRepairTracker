package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.Item;
import com.willbrinkman.BikeRepairTracker.models.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String showAllItems(Model model) {
        model.addAttribute("title", "All Items");
        model.addAttribute("items", itemRepository.findAll());
        return "items/index";
    }

    @GetMapping("new")
    public String showNewItemForm(Model model) {
        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        return "items/new";
    }

    @PostMapping("new")
    public String handleNewItemForm(@ModelAttribute @Valid Item newItem,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "items/new";
        }

        itemRepository.save(newItem);
        return "redirect:";
    }

    @GetMapping("view")
    public String showView(){

        return "items/view";
    }

    @GetMapping("view/{itemId}")
    public String showViewItem(Model model, @PathVariable int itemId) {

        Optional optItem = itemRepository.findById(itemId);
        if (optItem.isPresent()) {
            Item item = (Item) optItem.get();
            model.addAttribute("item", item);
            return "items/view";
        } else {
            return "redirect:../";
        }
    }
}
