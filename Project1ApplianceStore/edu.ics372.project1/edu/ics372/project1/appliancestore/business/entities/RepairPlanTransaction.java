package edu.ics372.project1.appliancestore.business.entities;

/**
 * A class for creating repair plan transaction objects.
 * 
 * @author Parker Talley.
 */
public class RepairPlanTransaction extends Transaction {
    private static final String REPAIR_PLAN_STRING = "REPAIR-PLAN";

    /**
     * Stores the appliance associated with repair plan transaction.
     */
    private ApplianceWithRepairPlan appliance;

    /**
     * Constructor for repair plan transactions.
     * @param customer Customer associated with repair plan transaction.
     * @param appliance ApplianceWithRepairPlan appliance associated with repair 
     * plan transaction.
     */
    public RepairPlanTransaction(Customer customer, ApplianceWithRepairPlan appliance) {
        super(customer);
        this.appliance = appliance;
        this.setId(generateId());
        this.setTotal(appliance.getRepairPlanAmount());
    }

    /**
     * Generates the next repair plan transaction ID.
     * @return String the next repair plan transaction ID.
     */
    private String generateId() {
        return REPAIR_PLAN_STRING + getNextId();
    }

    // Getters
    public ApplianceWithRepairPlan getAppliance() {
        return appliance;
    }
}
