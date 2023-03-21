package test;

import db.AccountingSystem;
import model.Apartment;
import model.Condo;
import model.House;
import model.Lease;
import model.Property;
import model.Property.propertyState;
import model.Tenant;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import controller.Controller;
import view.View;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class ModelTest {
	AccountingSystem model;
	View view;

	@Before
	public void init() {
		model = new AccountingSystem();
    	view = new View();
    	model.addObserver(view);
	}

    @Test
    public void addProperty_apartment() {
    	Property apt = new Apartment("judyStreet","Montreal", "H2K321",14,2,4,600.0);
    	System.out.println("APT TO STRING " + apt.toString());
    	apt.setState(propertyState.VacantReady);
    	model.addApartment(apt);
    	assertEquals(apt.toString(),model.getProperty(apt.getID()).toString());
    }
    
    @Test
    public void addProperty_condo() {
    	Property condo = new Condo("judyStreet","Montreal", "H2K321",14,2,4,600.0,61);
    	//System.out.println("CONDO TO STRING " + apt.toString());
    	condo.setState(propertyState.VacantReady);
    	model.addCondo(condo);
    	assertEquals(condo.toString(),model.getProperty(condo.getID()).toString());
    }
    
    @Test
    public void addProperty_house() {
    	Property house = new House("judyStreet","Montreal", "H2K321",14);
    	//System.out.println("CONDO TO STRING " + apt.toString());
    	house.setState(propertyState.VacantReady);
    	model.addHouse(house);
    	assertEquals(house.toString(),model.getProperty(house.getID()).toString());
    }

    @Test
    public void formatDate() {
        Controller util = new Controller(null,null);
        String inputDate = "13/03/23"; // DD/MM/YY format
        LocalDate expected = LocalDate.of(2023, 3, 13);
        LocalDate actual = util.formatDate(inputDate);
        assertEquals(expected, actual);
    }

    @Test
    public void addAndGetTenant() {
        String tenantData = "John,Doe";
        String[] tenantDataArray = tenantData.split(",");
        Tenant newTenant = new Tenant(tenantDataArray[0], tenantDataArray[1]);
        model.addTenant(newTenant);
        int tenantID = newTenant.getTenantId();
        Tenant retrievedTenant = model.getTenant(tenantID);
        assertNotNull(retrievedTenant);
        assertEquals(newTenant, retrievedTenant);
    }
    
    @Test 
    public void checkLeaseState() {
    	Property rentedHouse = new House("judyStreet","Montreal", "H2K321",14);
    	rentedHouse.setState(propertyState.Rented);
    	model.addHouse(rentedHouse);
    	
    	Property vacantReadyHouse = new House("judyStreet","Montreal", "H2K321",14);
    	vacantReadyHouse.setState(propertyState.VacantReady);
    	model.addHouse(vacantReadyHouse);
    	
    	Property vacantRenovation = new House("judyStreet","Montreal", "H2K321",14);
    	vacantRenovation.setState(propertyState.VacantRenovation);
    	model.addHouse(vacantRenovation);
    	
    	boolean success = true;
    	success &= !model.canLease(rentedHouse);
    	success &= model.canLease(vacantReadyHouse);
    	success &= !model.canLease(vacantRenovation);
    	
    	assertTrue(success);

    }
    
    @Test
    public void addLease() {
    	Controller control = new Controller(model,view);
    	
    	//Previous Tenant
    	Tenant oldTenant = new Tenant("David","oldDavid@live.com");
    	model.addTenant(oldTenant);
    	
    	//Property 
    	Property house = new House("judyStreet","Montreal", "H2K321",14);
    	house.setState(propertyState.Rented);
    	model.addHouse(house);
    	Lease lease = new Lease(oldTenant, house, control.formatDate("24/04/22"),control.formatDate("24/08/22"),1500.0);
    	model.addLease(lease);
    	
    	ArrayList<Lease> leaseList = model.getLeaseList();
    	
    	boolean hasLease = false;
    	for(Lease l: leaseList) {
    		hasLease = true;
    		assertEquals(l,lease);
    	}
    	assertTrue(hasLease);
    }
    
    @Test
    public void subscriptionSystem() {
    	Controller control = new Controller(model,view);
    	
    	//Previous Tenant
    	Tenant oldTenant = new Tenant("David","oldDavid@live.com");
    	model.addTenant(oldTenant);
    	
    	//Property 
    	Property house = new House("judyStreet","Montreal", "H2K321",14);
    	house.setState(propertyState.Rented);
    	model.addHouse(house);
    	
    	//Old Lease
    	Lease lease = new Lease(oldTenant, house, control.formatDate("24/04/22"),control.formatDate("24/08/22"),1500.0);
    	model.addLease(lease);
    	
    	//Add new tenant as a subscriber to the property
    	Tenant newTenant = new Tenant("John", "Johnis@live.com");
    	model.addTenant(newTenant);
    	model.addSubscription(newTenant, house);
    	
    	//Check for expired lease
    	model.checkLeaseStates();
    	assertEquals(house.getState(), propertyState.VacantReady);
    	
    }

    @Test
    public void getRentedUnits() {
		Property rentedHouse = new House("judyStreet","Montreal", "H2K321",14);
		rentedHouse.setState(propertyState.Rented);
		model.addHouse(rentedHouse);
		
		Property vacantReadyHouse = new House("judyStreet","Montreal", "H2K321",14);
		vacantReadyHouse.setState(propertyState.VacantReady);
		model.addHouse(vacantReadyHouse);
		
		Property vacantRenovation = new House("judyStreet","Montreal", "H2K321",14);
		vacantRenovation.setState(propertyState.VacantRenovation);
		model.addHouse(vacantRenovation);
		
		ArrayList<Property> rentedUnits = model.getRentedUnits();
    	boolean hasLease = false;
		for(Property rentedUnit : rentedUnits) {
			hasLease = true;
			assertEquals(rentedHouse.getID(), rentedUnit.getID());
		}
		assertTrue(hasLease);
    }

    @Test
    public void getVacantUnits() {
		Property rentedHouse = new House("judyStreet","Montreal", "H2K321",14);
		rentedHouse.setState(propertyState.Rented);
		model.addHouse(rentedHouse);
		
		Property vacantReadyHouse = new House("judyStreet","Montreal", "H2K321",14);
		vacantReadyHouse.setState(propertyState.VacantReady);
		model.addHouse(vacantReadyHouse);
		
		Property vacantRenovation = new House("judyStreet","Montreal", "H2K321",14);
		vacantRenovation.setState(propertyState.VacantRenovation);
		model.addHouse(vacantRenovation);
		
		ArrayList<Property> vacantUnits = model.getVacantUnits();
    	boolean hasLease = false;
		for(Property vacantUnit : vacantUnits) {
			hasLease = true;
			boolean validID = (vacantUnit.getID() == vacantRenovation.getID() || vacantUnit.getID() == vacantReadyHouse.getID());
			assertTrue(validID);
		}
		assertTrue(hasLease);
    }
    
    
    @Test
    public void getUnPaidRent() {
    	Controller control = new Controller(model,view);
    	
    	//Previous Tenant
    	Tenant paidTenant = new Tenant("David","oldDavid@live.com");
    	model.addTenant(paidTenant);
    	
    	//Property 
    	Property house = new House("judyStreet","Montreal", "H2K321",14);
    	house.setState(propertyState.Rented);
    	model.addHouse(house);
    	Lease paidLease = new Lease(paidTenant, house, control.formatDate("24/04/22"),control.formatDate("24/08/22"),1500.0);
    	paidLease.setMonthPaid(true);
    	model.addLease(paidLease);

    	//Previous Tenant
    	Tenant unpaidTenant = new Tenant("David","oldDavid@live.com");
    	model.addTenant(unpaidTenant);
    	
    	//Property 
    	Property unpaidHouse = new House("judyStreet","Montreal", "H2K321",14);
    	unpaidHouse.setState(propertyState.Rented);
    	model.addHouse(unpaidHouse);
    	Lease leaseUnpaid = new Lease(paidTenant, house, control.formatDate("24/04/22"),control.formatDate("24/08/22"),1500.0);
    	leaseUnpaid.setMonthPaid(false);
    	model.addLease(leaseUnpaid);
    	
    	ArrayList<Lease> leaseList = model.getUnpaidRent();
    	
    	boolean hasLease = false;
    	for(Lease l: leaseList) {
    		hasLease = true;
    		assertEquals(l,leaseUnpaid);
    	}
    	assertTrue(hasLease);
    }
    
    @Test
    public void getPaidRent() {
    	Controller control = new Controller(model,view);
    	
    	//Previous Tenant
    	Tenant paidTenant = new Tenant("David","oldDavid@live.com");
    	model.addTenant(paidTenant);
    	
    	//Property 
    	Property house = new House("judyStreet","Montreal", "H2K321",14);
    	house.setState(propertyState.Rented);
    	model.addHouse(house);
    	Lease paidLease = new Lease(paidTenant, house, control.formatDate("24/04/22"),control.formatDate("24/08/22"),1500.0);
    	paidLease.setMonthPaid(true);
    	model.addLease(paidLease);

    	//Previous Tenant
    	Tenant unpaidTenant = new Tenant("David","oldDavid@live.com");
    	model.addTenant(unpaidTenant);
    	
    	//Property 
    	Property unpaidHouse = new House("judyStreet","Montreal", "H2K321",14);
    	unpaidHouse.setState(propertyState.Rented);
    	model.addHouse(unpaidHouse);
    	Lease leaseUnpaid = new Lease(paidTenant, house, control.formatDate("24/04/22"),control.formatDate("24/08/22"),1500.0);
    	leaseUnpaid.setMonthPaid(false);
    	model.addLease(leaseUnpaid);
    	
    	ArrayList<Lease> leaseList = model.getPaidRent();
    	
    	boolean hasLease = false;
    	for(Lease l: leaseList) {
    		hasLease = true;
    		assertEquals(l,paidLease);
    	}
    	assertTrue(hasLease);
    }
}