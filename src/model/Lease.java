package model;

import model.Property.propertyState;
import java.time.LocalDate;

public class Lease {
    private Property unit;
    private Tenant tenant;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean monthIsPaid;
    private double rent;


    public Lease(Tenant tenant, Property unit, LocalDate startDate, LocalDate endDate, double rent) {
        this.unit = unit;
        this.tenant = tenant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rent = rent;
        this.monthIsPaid = false;
        unit.setState(propertyState.Rented);
    }
    
    

    public Property getUnit() {
        return unit;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getRent() {
        return rent;
    }

    public boolean monthPaid() {
        return monthIsPaid;
    }
    @Override
    public String toString() {
        return "Lease{" +
                "unit=" + unit +
                ", tenant=" + tenant +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rent=" + rent +
                ", monthPaid=" + monthIsPaid +

                '}';
    }
}

    

