package edu.ics372.project1.appliancestore.business.entities;

import java.io.Serializable;

/**
 * Repair plan class for creating repair plan objects.
 * 
 * @author Parker Talley.
 */
public class RepairPlan implements Serializable {
    /**
     * For serialization/de-serialization of the data.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * Stores reference to customer associated with repair plan.
     */
    private Customer customer;

    /**
     * Stores reference to appliance associated with repair plan.
     */
    private ApplianceWithRepairPlan appliance;

    /**
     * Creates a repair plan with the given customer and appliance.
     * 
     * @param customer Customer customer.
     * @param appliance Appliance appliance.
     */
    public RepairPlan(Customer customer, ApplianceWithRepairPlan appliance) {
        this.customer = customer;
        this.appliance = appliance;
    }

    // Getters
    public double getCost() {
        return appliance.getRepairPlanAmount();
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public ApplianceWithRepairPlan getAppliance() {
        return appliance;
    }
}
