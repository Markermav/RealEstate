package com.realestate.controller;

import com.realestate.model.Apartment;

public class addProperties_controller implements Property {

    public static void addApartment(Apartment aptObj) {
        addProperties_controller realEstate = new addProperties_controller();
        realEstate.addProperty(aptObj);
    }

    public static void addCondo(Object condObject) {
        addProperties_controller realEstate = new addProperties_controller();
        realEstate.addProperty(condObject);
    }

    public static void addHouse(Object houseObject) {
        addProperties_controller realEstate = new addProperties_controller();
        realEstate.addProperty(houseObject);
    }
}
