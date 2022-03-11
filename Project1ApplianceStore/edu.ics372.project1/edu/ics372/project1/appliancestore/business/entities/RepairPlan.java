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
    private Appliance appliance;

    /**
     * Stores the cost of the repair plan.
     */
    private double cost;

    /**
     * Creates a repair plan with the given customer and appliance.
     * @param customer Customer customer.
     * @param appliance Appliance appliance.
     */
    public RepairPlan(Customer customer, Appliance appliance) {
        this.customer = customer;
        this.appliance = appliance;
        /* TODO Here's the problem with repairCost not coming in. If we want it to be
        able to call the cost regardless of Aplliance, we need to put repair plan price
        field in the superclass Appliance. The set/gets could be abstract then and only
        defined in appliances that need to implement them. Thoughts?
        */
     //   this.cost = appliance.getRepairPlanCost();
    }

    /**
     * Getter for the cost a repair plan.
     * @return double cost of repair plan.
     */
    public double getCost() {
        return cost;
    }

    //Getters
    public Customer getCustomer() {
        return customer;
    }

    public Appliance getAppliance() {
        return appliance;
    }
}
