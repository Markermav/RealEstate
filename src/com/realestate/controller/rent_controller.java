package com.realestate.controller;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

import com.realestate.model.Apartment;
import com.realestate.model.Lease;
import com.realestate.model.Tenant;

public class rent_controller {

    static ArrayList<Object> leases = new ArrayList<>();

    public static void rent_unit() {
        addProperties_controller a = new addProperties_controller();
        ArrayList<Object> tennants = tenant_controller.displayAllTennats();
        ArrayList<Object> properties = a.getProperties();
        Object t = null;
        Object p = null;
        System.out.println(
                "Please provide the tenant Id & the property Id which they want to rent: separated by comma(,)");
        Scanner scannerTenant = new Scanner(System.in);
        String rentData = scannerTenant.nextLine();
        String[] rentDataArray = rentData.split(",");
        int tenantId = parseInt(rentDataArray[0]);
        int propertyId = parseInt(rentDataArray[1]);

        System.out.println("Please provide lease start date(DD/MM/YY), lease end date(DD/MM/YY) & rent price: separated by comma(,) ");
        Scanner scannerLeaseDetails = new Scanner(System.in);
        String leaseData = scannerLeaseDetails.nextLine();
        String[] leaseDataArray = leaseData.split(",");
        String startDate = leaseDataArray[0];
        String endDate = leaseDataArray[1];
        double rent = parseDouble(leaseDataArray[2]);

        for (Object i : tennants) {
            if (((Tenant) i).getTenantId() == tenantId) {
                t = i;
                break;
            }
        }
        for (Object i : properties) {
            if (((Apartment) i).getId() == propertyId) {
                ((Apartment) i).setRented(true);
                p = i;
                break;
            }
        }

        createLease(t, p, startDate, endDate, rent);

    }

    public static void createLease(Object tenant, Object property, String startDate, String endDate, double rent) {
        leases.add(new Lease(tenant, property, startDate, endDate, rent));
        System.out.println("Lease Created!!!");
    }

    public static ArrayList<Object> displayAllLeases(){
        return leases;
    }

    public static void isRented() {

    }
}
