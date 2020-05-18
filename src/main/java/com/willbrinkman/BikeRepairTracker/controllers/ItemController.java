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
import java.util.List;
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

    @Secured({ "ROLE_ADMIN" })
    @GetMapping("new")
    public String showNewItemForm(Model model) {
        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        return "items/new";
    }

    @Secured({ "ROLE_ADMIN" })
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

    @Secured({"ROLE_ADMIN"})
    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item Id:" + id));
        model.addAttribute("item", item);
        return "items/edit";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("edit/{id}")
    public String updateBike(@PathVariable("id") long id, Item newItem, @Valid Item item,
                             Errors errors, Model model, RedirectAttributes redirectAttrs, @RequestParam String name, @RequestParam Double itemAndRepairCost) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            return "items/edit";
        } else {
                newItem.setItemAndRepairCost(itemAndRepairCost);
                newItem.setName(name);
            itemRepository.save(newItem);

            }
            redirectAttrs.addAttribute(  "message","Item:"+ newItem.getName() +" has been updated!");
            redirectAttrs.addAttribute("newItemId", newItem.getId());
            return "redirect:/items/view/{newItemId}";
        }
    }


