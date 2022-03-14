package edu.ics372.project1.appliancestore.business.entities;

/*
 * @author Cristian Zendejas
 * The Washer subclass for Appliance.
 */
public class Washer extends ApplianceWithRepairPlans {
	//Creates a unique identifier for this subclass
	private static final String WASHER_STRING = "WASH";

	/*
	 * Creates a washer object
	 * @returns Washer object
	 */
	public Washer(String brandName, String model, double price, int quantity, double repairPlanAmount) {
		super(brandName, model, price, quantity, repairPlanAmount);
		this.setEligibleForRepairPlan(true);
		this.setEligibleForBackOrder(true);
	}

	@Override
	public String createId() {
		return WASHER_STRING + super.getNextId();
	}

}
