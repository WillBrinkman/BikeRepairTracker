package com.willbrinkman.BikeRepairTracker.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Currency;
import java.util.List;

@Entity
public class  Item extends AbstractEntity{

    public Item(){

    }


    @ManyToMany(mappedBy = "items")
    private List<Bike> bikes;



    private Double itemAndRepairCost;

    public Double getItemAndRepairCost() {
        return itemAndRepairCost;
    }

    public void setItemAndRepairCost(Double itemAndRepairCost) {
        this.itemAndRepairCost = itemAndRepairCost;
    }

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

}
