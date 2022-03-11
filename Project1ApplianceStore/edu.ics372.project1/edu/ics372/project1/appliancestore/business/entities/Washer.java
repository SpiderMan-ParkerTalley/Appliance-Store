package edu.ics372.project1.appliancestore.business.entities;

public class Washer extends Appliance {
	private double repairPlanAmount;

	public Washer(String brandName, String model, double price, int quantity, double repairPlanAmount) {
		super(brandName, model, price, quantity);
		this.repairPlanAmount = repairPlanAmount;
		this.setEligibleForRepairPlan(true);
		this.setEligibleForBackOrder(true);
	}

	public double getRepairPlanAmount() {
		return repairPlanAmount;
	}

	public void setRepairPlanAmount(double repairPlanAmount) {
		this.repairPlanAmount = repairPlanAmount;
	}
	
	@Override
	public String toString() {
		String output = super.toString() + " Repair Plan Amount: " + repairPlanAmount;
		return output;
	}
	
	
}
