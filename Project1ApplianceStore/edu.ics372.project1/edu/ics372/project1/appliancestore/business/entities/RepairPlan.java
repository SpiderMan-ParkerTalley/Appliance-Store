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
        //   this.cost = appliance.getRepairPlanCost(); TODO: NOT IMPLEMENTED
        /* Here's the problem with repairCost not coming in. If we want it to be
        able to call the cost regardless of Appliance i.e washer or dryer, we need to 
        put repair plan price field in the superclass Appliance. 
        The set/gets could be abstract then and only defined in appliances that need
         to implement them. That would mean that Appliance would need to be Abstract too.
         From the oracle docs page:
         Consider using abstract classes if any of these statements apply to your situation:
         You want to share code among several closely related classes.
         You expect that classes that extend your abstract class have many common methods or fields, 
         or require access modifiers other than public (such as protected and private).
         You want to declare non-static or non-final fields. This enables you to define methods that 
         can access and modify the state of the object to which they belong
         Thoughts?
        */

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
