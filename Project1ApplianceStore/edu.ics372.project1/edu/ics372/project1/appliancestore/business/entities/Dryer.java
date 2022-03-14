package edu.ics372.project1.appliancestore.business.entities;

/*
 * @author Cristian Zendejas
 * The Dryer subclass for Appliance.
 */
public class Dryer extends ApplianceWithRepairPlans {
	//Creates a unique identifier for this subclass
	private static final String DRYER_STRING = "DRY";

	/*
	 * Creates the Dryer object
	 * @returns Dryer object
	 */
	public Dryer(String brandName, String model, double price, int quantity, double repairPlanAmount) {
		super(brandName, model, price, quantity, repairPlanAmount);
		this.setEligibleForRepairPlan(true);
		this.setEligibleForBackOrder(true);
	}
	
	@Override
	public String createId() {
		return DRYER_STRING + super.getNextId();
	}

}
