package com.example.model;


/**
 * 
 * @author pablo, kalinga
 *
 */
public class Condo extends Apartment {
    private int streetNumber;

    public Condo(String streetAddress, String city, String postalCode, int unitNo, int bedrooms,
            int bathRooms, double squareFeet, int streetNumber) {
        super(streetAddress, city, postalCode, unitNo, bedrooms, bathRooms, squareFeet);
        this.streetNumber = streetNumber;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }



    @Override
    public String toString() {
        return "Condo {" +
                "streetAddress='" + getstreetName() + '\'' +
                ", city='" + getCity() + '\'' +
                ", state='" + getState().name() + '\'' +
                ", postalCode='" + getpostalCode() + '\'' +
                ", unitNo=" + getAptNumber() +
                ", streetNumber=" + streetNumber +
                ", bedrooms=" + getBedrooms() +
                ", bathRooms=" + getBathRooms() +
                ", squareFeet=" + getSquareFeet() +
                ", type='" + this.getType().toString() + '\'' +
                ", Property id=" + getID() + "\n" +
                '}';
    }

}
