package com.realestate.model;

public class Address {

        private String streetName;
        private String city;
        private String state;
        private String postalCode;
    
        public Address(String streetName, String city, String state, String postalCode) {
            this.streetName = streetName;
            this.city = city;
            this.state = state;
            this.postalCode = postalCode;
        }
    
        public String getstreetName() {
            return streetName;
        }
    
        public void setstreetName(String streetName) {
            this.streetName = streetName;
        }
    
        public String getCity() {
            return city;
        }
    
        public void setCity(String city) {
            this.city = city;
        }
    
        public String getState() {
            return state;
        }
    
        public void setState(String state) {
            this.state = state;
        }
    
        public String getpostalCode() {
            return postalCode;
        }
    
        public void setpostalCode(String postalCode) {
            this.postalCode = postalCode;
        }
    
        @Override
        public String toString() {
            return streetName + ", " + city + ", " + state + " " + postalCode;
        }
    }
    
    

