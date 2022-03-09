package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Dryer extends Appliance {
	private double repairPlanAmount;
	private List<Washer> itemsOnStock;

	public Dryer(String brandName, String model, double price, int quantity, double repairPlanAmount) {
		super(brandName, model, price, quantity);
		this.repairPlanAmount = repairPlanAmount;
		this.itemsOnStock = new ArrayList<Washer>();
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
