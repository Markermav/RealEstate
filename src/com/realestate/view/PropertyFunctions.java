package com.realestate.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.realestate.controller.Property;
import com.realestate.controller.addProperties_controller;
import com.realestate.controller.rent_controller;
import com.realestate.controller.tenant_controller;
import com.realestate.model.Apartment;
import com.realestate.model.Condo;
import com.realestate.model.Tenant;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PropertyFunctions {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Only one array of all employees and bills
        // ArrayList<IPayee> payeeList = new ArrayList<IPayee>();
        while (true) {
            try {
                // The menu list
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
                System.out.println("9. Exit");
                System.out.println("--------------REAL ESTATE--------------");

                System.out.print("Please Choose an option: ");
                String choose = sc.next();
                switch (choose) {
                    case "1" -> {
                        while (true) {
                            System.out.println("Please choose Property Type");
                            System.out.println("1. APARTMENT");
                            System.out.println("2. CONDO");
                            System.out.println("3. HOUSE");

                            String propertyType = sc.next();
                            if ("1".equals(propertyType)) {
                                System.out.print("Please provide Appartment Details: ");
                                System.out.print("Provide the Street name, city, state, postal code");
                                System.out.println(
                                        "unique Apartment Number, Bedrooms, Bathrooms and Total SquareFeet all separated by comma (,)");

                                Scanner scannerApartment = new Scanner(System.in);
                                String appartmentData = scannerApartment.nextLine();

                                String[] appartmentDataArray = appartmentData.split(",");

                                addProperties_controller.addApartment(new Apartment(appartmentDataArray[0],
                                        appartmentDataArray[1], appartmentDataArray[2], appartmentDataArray[3],
                                        parseInt(appartmentDataArray[4]), parseInt(appartmentDataArray[5]),
                                        parseInt(appartmentDataArray[6]), parseDouble(appartmentDataArray[7])));
                                ;
                                System.out.println("Appartment added!!");
                                System.out.println();
                                break;
                            } else if ("2".equals(propertyType)) {
                                System.out.print("Please provide Condo Details: ");
                                System.out.print("Provide the Street name, city, state, postal code,");
                                System.out.println(
                                        "unique Condo number, Bedrooms, Bathrooms, total SquareFeet & street number all separated by comma (,)");

                                Scanner scannerCondo = new Scanner(System.in);
                                String condoData = scannerCondo.nextLine();

                                String[] condoDataArray = condoData.split(",");

                                addProperties_controller.addCondo(new Condo(condoDataArray[0],
                                        condoDataArray[1], condoDataArray[2], condoDataArray[3],
                                        parseInt(condoDataArray[4]), parseInt(condoDataArray[5]),
                                        parseInt(condoDataArray[6]), parseDouble(condoDataArray[7]),
                                        parseInt(condoDataArray[8])));
                                ;
                                System.out.println("Condo added!!");
                                System.out.println();
                                break;
                            } else {
                                System.out.println("Input option is not proper, please choose only 1, 2 or 3");
                            }
                        }
                    }
                    case "2" -> {
                        tenant_controller.addTenant();
                        break;
                    }
                    case "3" -> {
                        rent_controller.rent_unit();
                        break;
                    }
                    case "4" -> {
                        addProperties_controller a = new addProperties_controller();
                        ArrayList<Object> properties = a.getProperties();

                        for (Object i : properties) {
                            System.out.println(i);
                            // if (i instanceof Apartment) {
                            // Apartment apt = (Apartment) i;
                            // System.out.println(apt.getSquareFeet());
                            // }
                        }
                    }
                    case "5" -> {
                        ArrayList<Object> tennants = tenant_controller.displayAllTennats();
                        for (Object i : tennants) {
                            System.out.println(i);
                            // if (i instanceof Apartment) {
                            // Apartment apt = (Apartment) i;
                            // System.out.println(apt.getSquareFeet());
                            // }
                        }
                    }
                    case "8" -> {
                        ArrayList<Object> leases = rent_controller.displayAllLeases();
                        for(Object i : leases) {
                            System.out.println(i);
                        }

                    }
                    case "9" -> {
                        // Quit system
                        System.out.println("Thanks,Bye!");
                        System.out.println("Exit!");
                        System.exit(0);
                    }
                    // Verification input information
                    default -> System.out.println("Please enter 1 to 9");
                }
            } catch (Exception e) {
                System.out.println("Opps! Exception occured : " + e + " Please Try Again!");
                // sc.nextLine();
            }
        }
    }

}
