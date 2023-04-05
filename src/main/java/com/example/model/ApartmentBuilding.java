package com.example.model;

import java.util.ArrayList;

//Rename this to apartment building
/**
 * 
 * @author pablo, kalinga
 *
 */
public class ApartmentBuilding extends PropertyComponent {
	private ArrayList<PropertyComponent> propertyComponents;
	private String buildingName;
	private String civicAddress;
	private Boolean highEnd;
	
	public ApartmentBuilding(String groupName, String civicAddress, Boolean highEnd) {
		this.buildingName = groupName;
		this.civicAddress = civicAddress;
		this.highEnd = highEnd;
		propertyComponents = new ArrayList<PropertyComponent>();
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public Boolean isHighEnd() {
		return highEnd;
	}
	
	public String getCivicAddress() {
		return civicAddress;
	}
	
	
	@Override
	public void addProperty(PropertyComponent property) {
		 propertyComponents.add(property);
    }
	
	@Override
    public void removeProperty(PropertyComponent property) {
    	propertyComponents.remove(property);
    }

	@Override
    public PropertyComponent getComponent(int componentIndex) {
    	return propertyComponents.get(componentIndex);
    }
    
	@Override
    public String toString() {
 	   StringBuilder sBuilder = new StringBuilder();
 	   sBuilder.append(String.format("**** {} ****\n** {} **\n** High End : {} **\n", buildingName,civicAddress,highEnd));
 	   for(PropertyComponent pc : propertyComponents) {
 		   sBuilder.append(pc.toString());
 	   }
 	   return sBuilder.toString();
    }
}
