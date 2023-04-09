package com.example.model;
/**
 * 
 * @author pablo, kalinga
 *
 */
public abstract class Property extends PropertyComponent {

		public enum propertyState {Rented, VacantReady, VacantRenovation}
        public enum propertyType {Apartment, House, Condo}
		private static int nextID = 1;
		protected int id;
        private String streetName;
        private String city;
        private propertyState state;

        private propertyType type;
        private String postalCode;
    
        public Property(String streetName, String city, String postalCode) {
            this.streetName = streetName;
            this.city = city;
            this.postalCode = postalCode;
            this.id = nextID++;
            this.state = propertyState.VacantReady;
            this.type = propertyType.Apartment;
        }
    
        public String getstreetName() {
            return streetName;
        }
    
        public void setstreetName(String streetName) {
            this.streetName = streetName;
        }
        
        public void setState(propertyState status) {
        	state = status;
        }

        public void setType(propertyType type) { this.type = type;}

        public propertyType getType() {return type;}
        
        public propertyState getState() {
        	return state;
        }
    
        public String getCity() {
            return city;
        }
        
        public int getID() {
        	return id;
        }
    
        public void setCity(String city) {
            this.city = city;
        }
        

        
        public Boolean getRented() {
        	return (state == propertyState.Rented);
        }
        
    
    
        public String getpostalCode() {
            return postalCode;
        }
    
        public void setpostalCode(String postalCode) {
            this.postalCode = postalCode;
        }
    
        @Override
        public String toString() {
            return streetName + ", " + city + ", " + state.name() + " " + postalCode;
        }
    }
    
    

