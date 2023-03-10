package db;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Lease;
import model.Property;
import model.PropertyComponent;
import model.Tenant;
import model.Property.propertyState;
import observer.Subject;

/**
 * 
 * @author pablo
 *Centralized real estate system storing property and tenant info.
 */
public class AccountingSystem extends Subject {
	private ArrayList<PropertyComponent> propertyList;
	private ArrayList<Tenant> tenantList;
	private ArrayList<Lease> leaseList;
	
	public AccountingSystem() {
		propertyList = new ArrayList<PropertyComponent>();
		tenantList = new ArrayList<Tenant>();
		leaseList = new ArrayList<Lease>();
	}

	public void addApartment(PropertyComponent apt) {
		propertyList.add(apt);
		broadcastEvent("ApartmentAddedEvent"+ "::" + apt);
	}
	public void addCondo(PropertyComponent condo) {
		propertyList.add(condo);
		broadcastEvent("CondoAddedEvent"+ "::" + condo);

    }
    public void addHouse(PropertyComponent house) {
    	propertyList.add(house);
		broadcastEvent("HouseAddedEvent" + "::" + house);

    }
    public void addTenant(Tenant tenant) {
    	tenantList.add(tenant);
		broadcastEvent("TenantAddedEvent"+ "::" + tenant);

    }
    public boolean canLease( Property prop) {
    	if(prop.getState() != Property.propertyState.VacantReady) {
    		return false;
    	}
    	return true;
    }
    
    
    public void addLease(Lease lease) {
    	leaseList.add(lease);
		broadcastEvent("LeaseAddedEvent"+ "::" + lease);
	
    }
    
    public void addSubscription(Tenant tenant, Property prop) {
    	tenant.setInterestedUnit(prop.getID());
    	broadcastEvent("SubscriptionAddedEvent"+"::"+ tenant.getEmail() + " subscribed to property " + prop.getID());
    }
    

    public Tenant getTenant(int tenantID) {
    	for(Tenant t: tenantList) {
    		if(t.getTenantId() == tenantID) {
    			return t;
    		}
    	}
    	return null;
    }
    
    public Property getProperty(int propertyID) {
    	for(PropertyComponent p : propertyList) {
    		Property prop = (Property)p;
    		if(prop !=null && prop.getID() == propertyID) {
    			return prop;
    		}
    	}
    	return null;
    }
	
    public final ArrayList<PropertyComponent> getPropertyList(){
    	return propertyList;
    }
    public final ArrayList<Tenant> getTenantList(){
    	return tenantList;
    }
    public final ArrayList<Lease> getLeaseList(){
    	return leaseList;
    }
    public final ArrayList<Property> getRentedUnits() {
    	ArrayList<Property> rentedUnits = new ArrayList<Property>();
    	for(PropertyComponent p: propertyList) {
    		Property prop = (Property) p;
    		if(prop != null && prop.getRented()) {
    			rentedUnits.add(prop);
    		}
    	}
    	return rentedUnits;
    }
    
    public final ArrayList<Property> getVacantUnits() {
    	ArrayList<Property> vacantUnits = new ArrayList<Property>();
    	for(PropertyComponent p: propertyList) {
    		Property prop = (Property) p;
    		if(prop != null && !prop.getRented()) {
    			vacantUnits.add(prop);
    		}
    	}
    	return vacantUnits;
    }

    public final ArrayList<Lease> getPaidRent() {
    	ArrayList<Lease> paidLeaseList = new ArrayList<Lease>();
    	for(Lease l: leaseList) {
            if(l.monthPaid()) {
                paidLeaseList.add(l);
            }
    	}
    	return paidLeaseList;
    }

    public final ArrayList<Lease> getUnpaidRent() {
    	ArrayList<Lease> unpaidLeaseList = new ArrayList<Lease>();
    	for(Lease l: leaseList) {
            if(!l.monthPaid()) {
            	unpaidLeaseList.add(l);
            }
    	}
    	return unpaidLeaseList;
    }
    
    public void emailSubscribers(int propertyID) {
    	StringBuilder subscribers = new StringBuilder();
    	for(Tenant t: tenantList) {
    		if(t.getInterestedInUnit() == propertyID) {
    			subscribers.append(t.toString()+"\n");
    		}
    	}
    	broadcastEvent("PropertyVacantEvent" + "::" + "Property: " + propertyID + " freed.\nEmail sent to:\n"+subscribers.toString());
    }
    
    public void checkLeaseStates() {
    	LocalDate nowTime =  LocalDate.now();
    	for(Lease l: leaseList) {
    		if(nowTime.isAfter(l.getEndDate())) {
    			Property prop = l.getUnit();
    			prop.setState(propertyState.VacantReady);
    			emailSubscribers(prop.getID());
    		}
    	}
    }
}

