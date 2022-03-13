package edu.ics372.project1.appliancestore.business.entities;

public class Dryer extends ApplianceWithRepairPlans {
	private static final String DRYER_STRING = "DRY";
//	private double repairPlanAmount;

	public Dryer(String brandName, String model, double price, int quantity, double repairPlanAmount) {
		super(brandName, model, price, quantity, repairPlanAmount);
//		this.repairPlanAmount = repairPlanAmount;
		this.setEligibleForRepairPlan(true);
		this.setEligibleForBackOrder(true);
	}
	
	@Override
	public String createId() {
		return DRYER_STRING + super.getNextId();
	}

//	public double getRepairPlanAmount() {
//		return repairPlanAmount;
//	}
//
//	public void setRepairPlanAmount(double repairPlanAmount) {
//		this.repairPlanAmount = repairPlanAmount;
//	}
	
//	@Override
//	public String toString() {
//		String output = super.toString() + " Repair Plan Amount: " + repairPlanAmount;
//		return output;
//	}

}
