package com.willbrinkman.BikeRepairTracker.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Currency;
import java.util.List;

@Entity
public class Item extends AbstractEntity{

    public Item(){

    }

    public Item(Double theItemCost, Double theRepairCost){
        this.itemCost = theItemCost;
        this.repairCost = theRepairCost;
    }

    @ManyToMany(mappedBy = "items")
    private List<Bike> bikes;

    private Double itemCost;

    private Double repairCost;

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }


    public Double getItemCost() {
        return itemCost;
    }

    public void setItemCost(Double itemCost) {
        this.itemCost = itemCost;
    }

    public Double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(Double repairCost) {
        this.repairCost = repairCost;
    }
}
