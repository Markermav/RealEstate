package com.realestate.model;

public class House extends Address{
    private int streetNumber;
    private String type = "house";

    // we may require to inherit the appartmrent class here!
    public House(String streetName, String city, String state, String postalCode, int streetNumber){
        super(streetName, city, state, postalCode);
        this. streetNumber = streetNumber;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    public String getType() {
        return type;
    }
    
}
