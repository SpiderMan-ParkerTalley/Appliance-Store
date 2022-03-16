package edu.ics372.project1.appliancestore.business.entities;

//TODO Cristian comments
public abstract class ApplianceWithRepairPlan extends Appliance {
	private double repairPlanAmount;

	public ApplianceWithRepairPlan(String brandName, String model, double price, int quantity, double repairPlanAmount) {
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
