package com.willbrinkman.BikeRepairTracker.controllers;

import com.willbrinkman.BikeRepairTracker.models.Bike;
import com.willbrinkman.BikeRepairTracker.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.util.Currency;

@Controller
@RequestMapping(value = "costForm")
public class CostFormController {


    public Double CostFormController(Item item, double theItemCost) {
        item.setItemCost(theItemCost);
        return item.getItemCost();
    }

    DecimalFormat money = new DecimalFormat("$#,##0.0");
}
