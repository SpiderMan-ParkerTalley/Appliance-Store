package edu.ics372.project1.appliancestore.business.entities;

/**
 * Dryer class for creating dryer object. Subclass of ApplianceWithRepairPlan.
 * 
 * @author Cristian Zendejas and Parker Talley.
 */
public class Dryer extends ApplianceWithRepairPlan {
	/**
	 * Stores the dryer ID code.
	 */
	private static final String DRYER_STRING = "DRY";

	/**
	 * Creates dryer object.
	 * 
	 * @param brandName String brand name of the dryer.
	 * @param model String model of the dryer.
	 * @param price double price of the dryer.
	 * @param quantity int quantity of the dryer.
	 * @param repairPlanAmount double repair plan cost/amount of dryer.
	 */
	public Dryer(String brandName, String model, double price, double repairPlanAmount) {
		super(brandName, model, price, repairPlanAmount);
		this.setEligibleForRepairPlan(true);
		this.setEligibleForBackOrder(true);
	}

	@Override
	public String createId() {
		return DRYER_STRING + super.getNextId();
	}

}
