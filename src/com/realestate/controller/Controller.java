package controller;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.util.ArrayList;

import db.AccountingSystem;
import model.Apartment;
import model.Condo;
import model.House;
import model.Lease;
import model.Property;
import model.Tenant;
import model.Property.propertyState;
import observer.Observer;
import view.View;

public class Controller  {
	private View view;
	private AccountingSystem model;
	
	public Controller(AccountingSystem model ,View view) {
		this.view = view;
		this.model = model;
	}
	
	
	
	public void run() {
		 while (true) {
	            try {
	                // The menu list
	                int choice = Integer.parseInt(view.displayMainMenu());
	                switch (choice) {
	                    case 1 -> {
	                        addProperty();
	                        break;
	                    }
	                    case 2 -> {
	                        addTenant();
	                        break;
	                    }
	                    case 3 -> {
	                        rentUnit();
	                        break;
	                    }
	                    case 4 -> {
	                        displayProperties();
	                        break;
	                    }
	                    case 5 -> {
	                    	displayTenants();
	                    	break;
	                    }
	                    case 6 -> {
	                    	displayRentedUnits();
	                    	break;
	                    }
	                    case 7 -> {
	                    	displayVacantUnits();
	                    	break;
	                    }
	                    case 8 -> {
	                        displayAllLeases();
	                        break;

	                    }
                        case 9 -> {
	                        displayPaidRents();
	                        break;

	                    }
                        case 10 -> {
	                        displayUnpaidRents();
	                        break;

	                    }
                        case 11 -> {
	                        checkState();
	                        break;

	                    }
	                    case 12 -> {
	                        quitSystem();
	                        break;
	                    }
	                    // Verification input information
	                    default -> view.invalidInput();
	                }
	            } catch (Exception e) {
	                System.out.println("Opps! Exception occured : " + e + " Please Try Again!");
	                // sc.nextLine();
	            }
	        }
	}
	
	private Property.propertyState getState(int i) {
		switch(i) {
		case 1:
			return propertyState.Rented;
		case 2:
			return propertyState.VacantReady;
		default:
			return propertyState.VacantRenovation;
		}
	}

	public void addProperty() {
		while (true) {
            String propertyType = view.propertyType();
            if ("1".equals(propertyType)) {
                String appartmentData = view.aptInput();
                String[] appartmentDataArray = appartmentData.split(",");
                Property prop = new Apartment(appartmentDataArray[0],
                        appartmentDataArray[1],  appartmentDataArray[2],
                        parseInt(appartmentDataArray[3]), parseInt(appartmentDataArray[4]),
                        parseInt(appartmentDataArray[5]), parseDouble(appartmentDataArray[6]));
                prop.setState(getState(Integer.parseInt(view.propertyState())));
                model.addApartment(prop);
                break;
            } else if ("2".equals(propertyType)) {
                String condoData = view.condoInput();
                String[] condoDataArray = condoData.split(",");
                Property prop = new Condo(condoDataArray[0],
                        condoDataArray[1], condoDataArray[2],
                        parseInt(condoDataArray[3]), parseInt(condoDataArray[4]),
                        parseInt(condoDataArray[5]), parseDouble(condoDataArray[6]),
                        parseInt(condoDataArray[7]));
                prop.setState(getState(Integer.parseInt(view.propertyState())));
                model.addCondo(prop);
                break;
            }  
            else if("3".equals(propertyType)) {
            	String houseData = view.houseInput();
            	String[] houseDataArray = houseData.split(",");
            	Property prop = new House(houseDataArray[0],
            			houseDataArray[1],houseDataArray[2],Integer.parseInt(houseDataArray[3]));
            	System.out.println(prop.toString());
                prop.setState(getState(Integer.parseInt(view.propertyState())));
                model.addHouse(prop);
                break;
            }
            else {
                view.invalidInput();
            }
        }
	}
	
	// (DD/MM/YY), lease end date(DD/MM/YY)
	public LocalDate formatDate(String s) {
		String[] dateInfo = s.split("/");
		return LocalDate.of(Integer.parseInt("20" +dateInfo[2]), Integer.parseInt(dateInfo[1]), Integer.parseInt(dateInfo[0]));
	}
	
	public void addTenant() {
		String tenantData = view.tenantInput();
		String[] tenantDataArray = tenantData.split(",");
		model.addTenant(new Tenant(tenantDataArray[0], tenantDataArray[1]));
	}
	
	public void rentUnit() {
		String rentData = view.idInput();
		String[] rentDataArray = rentData.split(",");
		int tenantId = parseInt(rentDataArray[0]);
        int propertyId = parseInt(rentDataArray[1]);
        Tenant tenant = model.getTenant(tenantId);
        Property property = (Property) model.getProperty(propertyId);
        boolean available = model.canLease(property);
        
        if(!available) {
        	view.displayMessage("The property is currently unavailable");
        	String subscribe = view.subscribeTenant();
        	if(subscribe.compareTo("1") == 0) {
        		model.addSubscription(tenant, property);
        	}
        }
        		
        if(available) {
        	 String leaseData = view.leaseInput();
             String[] leaseDataArray = leaseData.split(",");
             String startDate = leaseDataArray[0];
             String endDate = leaseDataArray[1];
             double rent = parseDouble(leaseDataArray[2]);
 
             if(tenant == null || property == null) {
             	view.invalidInput();
             }
             
             else {
            	 //Unsubscribe tenant from previous properties
            	 tenant.setInterestedUnit(-1);
              	 model.addLease(new Lease(tenant,property,formatDate(startDate),formatDate(endDate),rent));
             }   
        }
        
	}
	
	public void displayProperties() {
		ArrayList<Object> propertyList = new ArrayList<Object>(model.getPropertyList());
		view.displayList(propertyList);
	}
	
	public void displayTenants() {
		ArrayList<Object> tenantList = new ArrayList<Object>(model.getTenantList());
		view.displayList(tenantList);
	}
	
	private void displayAllLeases() {
		ArrayList<Object> leaseList = new ArrayList<Object>(model.getLeaseList());
		view.displayList(leaseList);
		
	}

	private void displayVacantUnits() {
		ArrayList<Object> vacantUnitList = new ArrayList<Object>(model.getVacantUnits());
		view.displayList(vacantUnitList);
	}
	
	public void displayRentedUnits() {
		ArrayList<Object> rentedUnitList = new ArrayList<Object>(model.getRentedUnits());
		view.displayList(rentedUnitList);
	}

    public void displayPaidRents() {
        ArrayList<Object> paidLeaseList = new ArrayList<Object>(model.getPaidRent());
		view.displayList(paidLeaseList);
    }

    public void displayUnpaidRents() {
        ArrayList<Object> unpaidLeaseList = new ArrayList<Object>(model.getUnpaidRent());
		view.displayList(unpaidLeaseList);
    }

    //Looks for state update (i.e. apt freed)
    public void checkState() {
    	model.checkLeaseStates();
    }
	
	private void quitSystem() {
		 view.exitMessage();
         System.exit(0);
	}

	
	
	
	
}
