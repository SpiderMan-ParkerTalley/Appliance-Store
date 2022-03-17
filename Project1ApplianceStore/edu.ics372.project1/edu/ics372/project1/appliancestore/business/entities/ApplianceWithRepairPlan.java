package edu.ics372.project1.appliancestore.business.entities;

/**
 * Abstract parent class for all appliance eligible for repair plans.
 * 
 * @author Cristian Zendejas and Parker Talley.
 */
public abstract class ApplianceWithRepairPlan extends Appliance {
	/**
	 * Stores the total cost of the repair plan.
	 */
	private double repairPlanAmount;

	/**
	 * Constructor for appliance with a repair plan.
	 * 
	 * @param brandName String the brand name of the appliance.
	 * @param model String the model of the appliance.
	 * @param price double the price of the appliance.
	 * @param repairPlanAmount double repair plan cost for the appliance.
	 */
	public ApplianceWithRepairPlan(String brandName, String model, double price, 
		double repairPlanAmount) {
			super(brandName, model, price);
			this.repairPlanAmount = repairPlanAmount;
	}

	// Getter
	public double getRepairPlanAmount() {
		return repairPlanAmount;
	}

	// Setter
	public void setRepairPlanAmount(double repairPlanAmount) {
		this.repairPlanAmount = repairPlanAmount;
	}
	
	/**
	 * String form of appliance.
	 */
	public String toString() {
		String output = super.toString() + " Repair Plan Amount: " + repairPlanAmount;
		return output;
	}

}
