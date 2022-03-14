package edu.ics372.project1.appliancestore.business.entities;

public class RepairPlanTransaction extends Transaction {
    private static final String REPAIR_PLAN_STRING = "REPAIR-PLAN";

    private ApplianceWithRepairPlan appliance;

    public RepairPlanTransaction(Customer customer, ApplianceWithRepairPlan appliance) {
        super(customer);
        this.appliance = appliance;
        this.setId(generateId());
        this.setTotal(appliance.getRepairPlanAmount());
    }

    private String generateId() {
        return REPAIR_PLAN_STRING + getNextId();
    }
}
