package com.willbrinkman.BikeRepairTracker.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bikesize extends AbstractEntity{

    public Bikesize(){
    }

    public Bikesize(List<Bike> bikes) {
        this.bikes = bikes;
    }

    @OneToMany
    @JoinColumn
    private List<Bike> bikes = new ArrayList<>();

    public List<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(List<Bike> bikes) {
        this.bikes = bikes;
    }

}
