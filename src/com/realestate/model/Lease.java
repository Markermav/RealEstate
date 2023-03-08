package com.realestate.model;


public class Lease {
    private Object unit;
    private Object tenant;
    private String startDate;
    private String endDate;
    private double rent;

    public Lease(Object tenant, Object unit, String startDate, String endDate, double rent) {
        this.unit = unit;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rent = rent;
    }

    public Object getUnit() {
        return unit;
    }

    public Object getTenant() {
        return tenant;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getRent() {
        return rent;
    }

    @Override
    public String toString() {
        return "Lease{" +
                "unit=" + unit +
                ", tenant=" + tenant +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rent=" + rent +
                '}';
    }
}

    

