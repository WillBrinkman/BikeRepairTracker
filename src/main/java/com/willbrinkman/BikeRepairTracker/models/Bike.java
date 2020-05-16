package com.willbrinkman.BikeRepairTracker.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bike extends AbstractEntity{


    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Bikemodel bikemodel;

    @ManyToOne
    private Bikesize bikesize;

    @ManyToMany
    private List<Item> items = new ArrayList<>();

    public Bike() {
    }

    public Bike(Manufacturer aManufacturer, Bikemodel aBikemodel, Bikesize aBikesize, List<Item> theItems) {
        super();
        this.manufacturer = aManufacturer;
        this.bikemodel = aBikemodel;
        this.bikesize = aBikesize;
        this.items = theItems;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }


    public Bikemodel getBikemodel() {
        return bikemodel;
    }

    public void setBikemodel(Bikemodel bikemodel) {
        this.bikemodel = bikemodel;
    }


    public Bikesize getBikesize() {
        return bikesize;
    }

    public void setBikesize(Bikesize bikesize) {
        this.bikesize = bikesize;
    }



    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
