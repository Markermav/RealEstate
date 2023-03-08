package com.realestate.model;

public class Apartment extends Address {

    private int aptNumber;
    private int bedrooms;
    private int bathRooms;
    private double squareFeet;
    private String type = "appartment";
    private Boolean rented = false;
    private int id;
    private static int count = 0;

    public Apartment(String streetName, String city, String state, String postalCode, int aptNumber, int bedrooms,
            int bathRooms, double squareFeet) {
        super(streetName, city, state, postalCode);
        this.aptNumber = aptNumber;
        this.bedrooms = bedrooms;
        this.bathRooms = bathRooms;
        this.squareFeet = squareFeet;
        this.id = ++count;
    }

    public Boolean getRented() {
        return rented;
    }

    public void setRented(Boolean rented) {
        this.rented = rented;
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

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", type='" + type + '\'' +
                ", rented=" + rented +
                ", Property id=" + id + "\n" +
                "---------------------------------------";
    }

}
