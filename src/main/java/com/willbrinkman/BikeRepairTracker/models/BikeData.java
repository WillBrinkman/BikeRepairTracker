package com.willbrinkman.BikeRepairTracker.models;

import java.util.ArrayList;

public class BikeData {
    public static ArrayList<Bike> findFilterAndValue(String column, String value, Iterable<Bike> allBikes) {

        ArrayList<Bike> results = new ArrayList<>();

        if (value.toLowerCase().equals("all")){
            return (ArrayList<Bike>) allBikes;
        }

        if (column.equals("all")){
            results = findByValue(value, allBikes);
            return results;
        }
        for (Bike bike : allBikes) {

            String Value = getSelectedValue(bike, column);

            if (Value != null && Value.toLowerCase().contains(value.toLowerCase())) {
                results.add(bike);
            }
        }

        return results;
    }

    public static String getSelectedValue(Bike bike, String selected){
        String Value = "";
        if(selected.equals("id")){
            Value = String.valueOf(bike.getId());
        } else if (selected.equals("manufacturer")){
            Value = bike.getManufacturer().toString();
        } else if (selected.equals("bikemodel")){
            Value = bike.getBikemodel().toString();
        } else if (selected.equals("bikesize")){
            Value = bike.getBikesize().toString();
        } else {
            Value = bike.getItems().toString();
        }

        return Value;
    }

    public static ArrayList<Bike> findByValue(String value, Iterable<Bike> allBikes) {
        ArrayList<Bike> results = new ArrayList<>();
        for (Bike bike : allBikes) {
            if (bike.getName().toLowerCase().contains(value.toLowerCase())) {
                results.add(bike);
            } else if (bike.getManufacturer().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(bike);
            } else if (bike.getBikemodel().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(bike);
            } else if (bike.getBikesize().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(bike);
            } else if (bike.getItems().toString().toLowerCase().contains(value.toLowerCase())) {
                results.add(bike);
            }
        }
        return results;

    }
}
