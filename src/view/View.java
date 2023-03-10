package view;

import java.util.ArrayList;
import java.util.Scanner;

import observer.Observer;

public class View implements Observer {
	private Scanner sc;
	
	public View() {
		sc = new Scanner(System.in);
	}

	public String displayMainMenu() {
		//add loop for invalid number handling here		
		System.out.println("Welcome to the Real Estate Accounting System");
        System.out.println("--------------Main Menu--------------");
        System.out.println("1. Add a Property");
        System.out.println("2. Add a Tenant");
        System.out.println("3. Rent a unit");
        System.out.println("4. Display Properties");
        System.out.println("5. Display Tenants");
        System.out.println("6. Display Rented Units");
        System.out.println("7. Display Vacant units");
        System.out.println("8. Display all leases");
        System.out.println("9. Display all paid rent");
        System.out.println("10. Display all unpaid rent");
        System.out.println("11. Check for status change");
        System.out.println("12. Exit");
        System.out.println("--------------REAL ESTATE--------------");
        System.out.print("Please Choose an option: ");
        String output = sc.next();
        sc.nextLine();
        return output;
	}

	public String propertyType() {
        System.out.println("Please choose Property Type");
        System.out.println("1. APARTMENT");
        System.out.println("2. CONDO");
        System.out.println("3. HOUSE");
        String output = sc.next();
        sc.nextLine();
        return output;
	}
	
	public String propertyState() {
		System.out.println("Please choose the state of the Property");
        System.out.println("1. Rented");
        System.out.println("2. Vacant and ready");
        System.out.println("3. Vacant and needs renovation");
        String output = sc.next();
        sc.nextLine();
        return output;
	}

	public String aptInput() {
		System.out.print("Please provide Appartment Details: ");
        System.out.print("Provide the Street name, city, postal code");
        System.out.println("unique Apartment Number, Bedrooms, Bathrooms and Total SquareFeet all separated by comma (,)");
        return sc.nextLine();

	}

	public String condoInput() {
		System.out.print("Please provide Condo Details: ");
        System.out.print("Provide the Street name, city, postal code,");
        System.out.println("unique Condo number, Bedrooms, Bathrooms, total SquareFeet & street number all separated by comma (,)");
        return sc.nextLine();
	}
	
	public String houseInput() {
		System.out.print("Please provide House Details: ");
        System.out.print("Provide the Street name,  city, postal code, street number- all separated by comma (,)\n");
        return sc.nextLine();
	}
	
	public String tenantInput() {
		System.out.println("Provide Tenant Details: ");
        System.out.print("Please provide Tenant's name and email separated by comma (,) : ");
        return sc.nextLine();
	}
	
	public String idInput() {
		System.out.println("Please provide the tenant Id & the property Id which they want to rent: separated by comma(,)");
        return sc.nextLine();
	}
	
	public String leaseInput() {
		System.out.println("Please provide lease start date(DD/MM/YY), lease end date(DD/MM/YY) & rent price: separated by comma(,) ");
        return sc.nextLine();
	}
	
	public String subscribeTenant() {
		System.out.println("Would you like to receive notifications when the property becomes available?");
		System.out.println("1: Yes");
		System.out.println("2: No");
        return sc.nextLine();
	}
	
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
	public void displayList(final ArrayList<Object> objects) {
		for(Object o : objects) {
			System.out.println(o);
		}
	}
	
	public void exitMessage() {
		 System.out.println("Thank you for using our system, Bye!");
         System.out.println("Exit!");
	}
	
	public void invalidInput() {
		System.out.println("Input is invalid, verify that the data you inputted is valid");
	}

	@Override
	public void update(Object args) {
		//A switch case can be used on the event type variable to invoke
		// specific methods depending on the type of event if needed.
		String[] eventData = ((String)args).split("::");
		if(eventData == null) { return;}
		String eventType = eventData[0];
		String eventInfo = eventData[1];
		displayMessage("\n" + eventType + ":\n" + eventInfo + "\n");
	}
	
}
