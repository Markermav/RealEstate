package com.realestate.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.realestate.model.Tenant;

public class tenant_controller {

    static ArrayList<Object> tenantList = new ArrayList<>();

    public static void addTenant() {
        System.out.println("Provide Tenant Details: ");
        System.out.print("Please provide Tenant's name and email separated by comma (,) : ");
        Scanner scannerTenant = new Scanner(System.in);
        String tenantData = scannerTenant.nextLine();
        String[] tenantDataArray = tenantData.split(",");
        tenantList.add(new Tenant(tenantDataArray[0], tenantDataArray[1]));
        System.out.println("Tenant added!!");
        System.out.println();

    }

    public static ArrayList<Object> displayAllTennats(){
        return tenantList;
    }
}
