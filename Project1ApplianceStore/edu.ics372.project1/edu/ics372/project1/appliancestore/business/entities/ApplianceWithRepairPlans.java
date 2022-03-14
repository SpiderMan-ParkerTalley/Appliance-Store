package edu.ics372.project1.appliancestore.business.entities;

/*
 * @author Cristian Zendejas
 * The  abstract subclass for appliances that use a repair plan.
 */
public abstract class ApplianceWithRepairPlans extends Appliance {
	//Stores the amount for the repair plan cost
	private double repairPlanAmount;
	
	
	/*
	 * Creates an appliance with a repair plan
	 * @return ApplianceWithRepairPlans
	 */
	public ApplianceWithRepairPlans(String brandName, String model, double price, int quantity, double repairPlanAmount) {
		super(brandName, model, price, quantity);
		this.repairPlanAmount = repairPlanAmount;
	}

	public double getRepairPlanAmount() {
		return repairPlanAmount;
	}

	public void setRepairPlanAmount(double repairPlanAmount) {
		this.repairPlanAmount = repairPlanAmount;
	}
	
	public String toString() {
		String output = super.toString() + " Repair Plan Amount: " + repairPlanAmount;
		return output;
	}

}
