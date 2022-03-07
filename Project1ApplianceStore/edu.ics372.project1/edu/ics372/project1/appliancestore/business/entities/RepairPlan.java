package edu.ics372.project1.appliancestore.business.entities;

public class RepairPlan {
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
