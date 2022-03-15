package edu.ics372.project1.appliancestore.business.entities;

import java.io.Serializable;

/**
 * Repair plan class for creating repair plan objects.
 * 
 * @author Parker Talley.
 */
public class RepairPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Stores reference to customer associated with repair plan.
     */
    private Customer customer;

    /**
     * Stores reference to appliance associated with repair plan.
     */
    private Appliance appliance;

    /**
     * Creates a repair plan with the given customer and appliance.
     * 
     * @param customer Customer customer.
     * @param appliance2 Appliance appliance.
     */
    public RepairPlan(Customer customer, ApplianceWithRepairPlan appliance2) {
        this.customer = customer;
        this.appliance = appliance2;
    }

    // Getters
    public double getCost() {
        return ((ApplianceWithRepairPlan) appliance).getRepairPlanAmount();
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public ApplianceWithRepairPlan getAppliance() {
        return (ApplianceWithRepairPlan) appliance;
    }
}
