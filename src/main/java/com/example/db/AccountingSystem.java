package com.example.db;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.model.*;
import com.example.model.Property.propertyState;
import com.example.observer.Subject;

/**
 * 
 * @author pablo
 *Centralized real estate system storing property and tenant info.
 */
public class AccountingSystem extends Subject {
	private final static AccountingSystem instance = new AccountingSystem();
	private ArrayList<PropertyComponent> propertyList;
	private ArrayList<Tenant> tenantList;
	private ArrayList<Lease> leaseList;

	public static AccountingSystem getInstance() {
		return instance;
	}
	private AccountingSystem() {
		propertyList = new ArrayList<PropertyComponent>();
		tenantList = new ArrayList<Tenant>();
		leaseList = new ArrayList<Lease>();
	}

	public synchronized void addApartment(PropertyComponent apt) {
		System.out.println("MODEL THREAD: " + Thread.currentThread());

		propertyList.add(apt);
		broadcastEvent("ApartmentAddedEvent"+ "::" + apt);
	}

	public synchronized void addCondo(PropertyComponent condo) {
		System.out.println("MODEL THREAD: " + Thread.currentThread());

		propertyList.add(condo);
		broadcastEvent("CondoAddedEvent"+ "::" + condo);

    }
    public synchronized void addHouse(PropertyComponent house) {
		System.out.println("MODEL THREAD: " + Thread.currentThread());

		propertyList.add(house);
		broadcastEvent("HouseAddedEvent" + "::" + house);

    }
    public synchronized void addTenant(Tenant tenant) {
		System.out.println("MODEL THREAD: " + Thread.currentThread());

		tenantList.add(tenant);
		broadcastEvent("TenantAddedEvent"+ "::" + tenant);

    }
    public boolean canLease( Property prop) {



		if(prop.getState() != Property.propertyState.VacantReady) {
			return false;
		}
		return true;
    }
    
    
    public synchronized void addLease(Lease lease) {
		System.out.println("MODEL THREAD: " + Thread.currentThread());


		leaseList.add(lease);
		broadcastEvent("LeaseAddedEvent"+ "::" + lease);

	}
    
    public synchronized void addSubscription(Tenant tenant, Property prop) {
		System.out.println("MODEL THREAD: " + Thread.currentThread());


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
		System.out.println("MODEL THREAD: " + Thread.currentThread());


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
		System.out.println("MODEL THREAD: " + Thread.currentThread());


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
		System.out.println("MODEL THREAD: " + Thread.currentThread());


		ArrayList<Lease> paidLeaseList = new ArrayList<Lease>();
		for(Lease l: leaseList) {
            if(l.monthPaid()) {
                paidLeaseList.add(l);
            }
		}
		return paidLeaseList;
    }

    public final ArrayList<Lease> getUnpaidRent() {
		System.out.println("MODEL THREAD: " + Thread.currentThread());


		ArrayList<Lease> unpaidLeaseList = new ArrayList<Lease>();
		for(Lease l: leaseList) {
            if(!l.monthPaid()) {
				unpaidLeaseList.add(l);
            }
		}
		return unpaidLeaseList;
    }
    
    public void emailSubscribers(int propertyID) {
		System.out.println("MODEL THREAD: " + Thread.currentThread());


		StringBuilder subscribers = new StringBuilder();
		for(Tenant t: tenantList) {
			if(t.getInterestedInUnit() == propertyID) {
				subscribers.append(t.toString()+"\n");
			}
		}
		broadcastEvent("PropertyVacantEvent" + "::" + "Property: " + propertyID + " freed.\nEmail sent to:\n"+subscribers.toString());
    }
    
    public void checkLeaseStates() {
		System.out.println("MODEL THREAD: " + Thread.currentThread());

		LocalDate nowTime =  LocalDate.now();
		for(Lease l: leaseList) {
			if(nowTime.isAfter(l.getEndDate())) {
				Property prop = l.getUnit();
				prop.setState(propertyState.VacantReady);
				emailSubscribers(prop.getID());
			}
		}
    }

	public void initSampleData() {
		//Tenant Data
		String[] TenantName = {"John Smith", "Emily Johnson", "Alex Rodriguez", "Jennifer Brown", "Robert Davis", "Melissa Garcia", "Daniel Wilson", "Jessica Martinez", "Kevin Taylor", "Amanda Anderson",
				"Michael Lee", "Elizabeth Martin", "David Hernandez", "Sarah Gonzalez", "Christopher Baker", "Ashley Campbell", "Brian Thomas", "Samantha Perez", "Matthew Scott", "Katherine Parker",
				"William Clark", "Isabella Adams", "Anthony Lewis", "Olivia Rodriguez", "Steven King", "Natalie Wright", "Josephine Phillips", "Joshua Turner", "Charlotte Jackson", "Brandon Hill",
				"Thomas Allen", "Madison Stewart", "Mark Carter", "Hannah Sanchez", "Andrew Miller", "Victoria Thompson", "George Flores", "Lauren Mitchell", "Nicholas Coleman", "Megan Nelson",
				"Daniel Ramirez", "Anna Cooper", "Frank Wright", "Evelyn Johnson", "Patrick Murphy", "Grace Turner", "Tyler Hall", "Avery Perez", "Richard Baker", "Chloe Scott"};
		String[] TenantMail = {"john.smith@gmail.com", "emily.johnson@yahoo.com", "arodriguez@hotmail.com", "jennifer.brown@outlook.com", "robert.davis@gmail.com", "melissa.garcia@yahoo.com", "daniel.wilson@hotmail.com", "jessica.martinez@gmail.com", "kevin.taylor@yahoo.com", "amanda.anderson@outlook.com",
				"michael.lee@gmail.com", "elizabeth.martin@yahoo.com", "david.hernandez@hotmail.com", "sarah.gonzalez@gmail.com", "christopher.baker@yahoo.com", "ashley.campbell@hotmail.com", "brian.thomas@gmail.com", "samantha.perez@yahoo.com", "matthew.scott@hotmail.com", "katherine.parker@gmail.com",
				"william.clark@gmail.com", "isabella.adams@yahoo.com", "anthony.lewis@hotmail.com", "olivia.rodriguez@gmail.com", "steven.king@yahoo.com", "natalie.wright@hotmail.com", "josephine.phillips@gmail.com", "joshua.turner@yahoo.com", "charlotte.jackson@hotmail.com", "brandon.hill@gmail.com",
				"thomas.allen@gmail.com", "madison.stewart@yahoo.com", "mark.carter@hotmail.com", "hannah.sanchez@gmail.com", "andrew.miller@yahoo.com", "victoria.thompson@hotmail.com", "george.flores@gmail.com", "lauren.mitchell@yahoo.com", "nicholas.coleman@hotmail.com", "megan.nelson@gmail.com",
				"daniel.ramirez@gmail.com", "anna.cooper@yahoo.com", "frank.wright@hotmail.com", "evelyn.johnson@gmail.com", "patrick.murphy@yahoo.com", "grace.turner@hotmail.com", "tyler.hall@gmail.com", "avery.perez@yahoo.com", "richard.baker@hotmail.com", "chloe.scott@gmail.com"};
		for(int i=0; i < Math.min(TenantName.length, TenantMail.length); ++i) {
			Tenant newTenant = new Tenant(TenantName[i], TenantMail[i]);
			addTenant(newTenant);
		}

		//House Data
		String[] StreetNameArray = {"First Avenue", "Second Street", "Third Avenue", "Fourth Street", "Fifth Avenue", "Sixth Street", "Seventh Avenue", "Eighth Street", "Ninth Avenue", "Tenth Street",
				"Elm Street", "Pine Street", "Cedar Avenue", "Willow Street", "Oak Street", "Maple Avenue", "Cherry Street", "Birch Avenue", "Magnolia Street", "Poplar Avenue",
				"Sunset Boulevard", "Hollywood Boulevard", "Vine Street", "Rodeo Drive", "Beverly Boulevard", "Melrose Avenue", "Abbott Kinney Boulevard", "Santa Monica Boulevard", "Wilshire Boulevard", "Sunset Strip",
				"Wall Street", "Broad Street", "State Street", "Madison Avenue", "Lexington Avenue", "Park Avenue", "Fifth Avenue", "Sixth Avenue", "Seventh Avenue", "Eighth Avenue",
				"Kingsway", "Bloor Street", "Yonge Street", "Queen Street", "Dundas Street", "Bay Street", "University Avenue", "Spadina Avenue", "Front Street", "Wellington Street"};

		String[] CityArray = {"New York", "Los Angeles", "Chicago", "Houston", "Philadelphia", "Phoenix", "San Antonio", "San Diego", "Dallas", "San Jose",
				"Boston", "Seattle", "Denver", "Nashville", "Atlanta", "Miami", "New Orleans", "Las Vegas", "Portland", "Austin",
				"London", "Paris", "Rome", "Madrid", "Barcelona", "Amsterdam", "Berlin", "Munich", "Vienna", "Stockholm",
				"Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide", "Auckland", "Wellington", "Christchurch", "Queenstown", "Rotorua",
				"Toronto", "Vancouver", "Montreal", "Ottawa", "Calgary", "Edmonton", "Halifax", "Quebec City", "Winnipeg", "Victoria"};

		String[] PostalCodeArray = {"10001", "10002", "10003", "10004", "10005", "10006", "10007", "10008", "10009", "10010",
				"90001", "90002", "90003", "90004", "90005", "90006", "90007", "90008", "90009", "90010",
				"60601", "60602", "60603", "60604", "60605", "60606", "60607", "60608", "60609", "60610",
				"77001", "77002", "77003", "77004", "77005", "77006", "77007", "77008", "77009", "77010",
				"M5G 2C2", "V6C 3K4", "H3B 4W5", "K1P 5E1", "T2P 2M5", "M5H 2N2", "V5Z 1K7", "H2Z 1E3", "R3C 3N2", "V8W 1H9"};

		String[] StreetNumberArray = {"123", "456", "789", "1011", "1213", "1415", "1617", "1819", "2021", "2223", "2425"};
		for(int i=0; i < CityArray.length; ++i) {
			String streetName = StreetNameArray[i%StreetNameArray.length];
			String cityName = CityArray[i%CityArray.length];
			String postalCode = PostalCodeArray[i% PostalCodeArray.length];
			String streetNumber = StreetNumberArray[i% StreetNumberArray.length];
			Property newHouse = new House(streetName, cityName, postalCode, Integer.parseInt(streetNumber));
			addHouse(newHouse);
		}
	}

}

