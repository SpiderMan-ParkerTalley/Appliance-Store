package edu.ics372.project1.appliancestore.business.entities;

/**
 * Washer class for creating washer objects.
 * 
 * @author Cristian Zendejas and Parker Talley
 */
public class Washer extends ApplianceWithRepairPlan {
	/**
	 * Stores the washer ID code.
	 */
	private static final String WASHER_STRING = "WASH";

	/**
	 * Creates washer object.
	 * 
	 * @param brandName String brand name of the washer.
	 * @param model String model of the washer.
	 * @param price double price of the washer.
	 * @param repairPlanAmount double repair plan cost/amount of washer.
	 */
	public Washer(String brandName, String model, double price, double repairPlanAmount) {
		super(brandName, model, price, repairPlanAmount);
		this.setEligibleForRepairPlan(true);
		this.setEligibleForBackOrder(true);
	}

	@Override
	public String createId() {
		return WASHER_STRING + super.getNextId();
	}

}
