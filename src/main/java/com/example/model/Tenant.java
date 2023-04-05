package com.example.model;

/**
 * 
 * @author pablo, kalinga
 *
 */
public class Tenant {

    private int tenantId;
    private String name;
    private String email;
    private static int count = 0;
    private int interestedUnit;

    public Tenant(String name, String email) {
        this.name = name;
        this.email = email;
        this.tenantId = ++count;
        this.interestedUnit = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getInterestedInUnit() {
        return interestedUnit;
    }

    public void setInterestedUnit(int interestedInUnit) {
        this.interestedUnit = interestedInUnit;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "Tenant {" +
                "tenantId=" + tenantId +
                ", name=" + name +
                ", email=" + email +
                '}';
    }

}
