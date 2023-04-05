package com.example.model;

/**
 * 
 * @author pablo, kalinga
 *
 */

public class Apartment extends Property {

    private int aptNumber;
    private int bedrooms;
    private int bathRooms;
    private double squareFeet;

    public Apartment(String streetName, String city, String postalCode, int aptNumber, int bedrooms,
            int bathRooms, double squareFeet) {
        super(streetName, city, postalCode);
        this.aptNumber = aptNumber;
        this.bedrooms = bedrooms;
        this.bathRooms = bathRooms;
        this.squareFeet = squareFeet;
    }


    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public double getSquareFeet() {
        return squareFeet;
    }

    public void setSquareFeet(double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public int getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(int aptNumber) {
        this.aptNumber = aptNumber;
    }




    @Override
    public String toString() {
        return "--------------Apartment-----------------"
                + "\n" +
                "streetAddress='" + getstreetName() + '\'' +
                ", city='" + getCity() + '\'' +
                ", postalcode='" + getpostalCode() + '\'' +
                "aptNumber=" + aptNumber +
                ", bedrooms=" + bedrooms +
                ", bathRooms=" + bathRooms +
                ", squareFeet=" + squareFeet +
                ", rented=" + getRented() +
                ", state= " + getState().name() +
                ", Property id=" + getID() + "\n" +
                "---------------------------------------";
    }

}
