package edu.ics372.project1.appliancestore.business.entities;

public abstract class ApplianceWithRepairPlans extends Appliance {
	private double repairPlanAmount;

	public ApplianceWithRepairPlans(String brandName, String model, double price, int quantity, double repairPlanAmount) {
		super(brandName, model, price, quantity);
		this.repairPlanAmount = repairPlanAmount;
		
	}

	public double getRepariPlanAmount() {
		return repairPlanAmount;
	}

	public void setRepariPlanAmount(double repariPlanAmount) {
		this.repairPlanAmount = repariPlanAmount;
	}
	
	public String toString() {
		String output = super.toString() + " Repair Plan Amount: " + repairPlanAmount;
		return output;
	}

}
