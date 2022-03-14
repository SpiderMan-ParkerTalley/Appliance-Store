package edu.ics372.project1.appliancestore.business.entities;

import java.io.Serializable;

public class RepairPlan implements Serializable {
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
     * @param customer Customer customer.
     * @param appliance Appliance appliance.
     */
    public RepairPlan(Customer customer, ApplianceWithRepairPlan appliance) {
        this.customer = customer;
        this.appliance = appliance;
    }

    /**
     * Getter for the cost a repair plan.
     * @return double cost of repair plan.
     */
    public double getCost() {
        return appliance.getRepairPlanAmount();
    }

    //Getters
    public Customer getCustomer() {
        return customer;
    }

    public ApplianceWithRepairPlan getAppliance() {
        return appliance;
    }
}
