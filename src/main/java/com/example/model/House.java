package com.example.model;
/**
 * 
 * @author pablo, kalinga
 *
 */
public class House extends Property{
    private int streetNumber;

    public House(String streetName, String city, String postalCode, int streetNumber){
        super(streetName, city, postalCode);
        this. streetNumber = streetNumber;
    }
    public int getStreetNumber() {
        return streetNumber;
    }
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    @Override
    public String toString() {
        return "--------------House-----------------"
                + "\n" +
                "streetAddress='" + getstreetName() + '\'' +
                ", city='" + getCity() + '\'' +
                ", postalcode='" + getpostalCode() + '\'' +
                ", rented=" + getRented() +
                ", state= " + getState().name() +
                ", Property id=" + getID() + "\n" +
                "---------------------------------------";
    }
    
}
