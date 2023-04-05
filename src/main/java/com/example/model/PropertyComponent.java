package com.example.model;

/**
 * 
 * @author pablo, kalinga
 *
 */
public abstract class PropertyComponent {
	   public void addProperty(PropertyComponent property) {
       		throw new UnsupportedOperationException();

       }
       
       public void removeProperty(PropertyComponent property) {
       		throw new UnsupportedOperationException();

       }

       public PropertyComponent getComponent(int componentIndex) {
       		throw new UnsupportedOperationException();
       }
       
       public String toString() {
    	   throw new UnsupportedOperationException();
       }
}
