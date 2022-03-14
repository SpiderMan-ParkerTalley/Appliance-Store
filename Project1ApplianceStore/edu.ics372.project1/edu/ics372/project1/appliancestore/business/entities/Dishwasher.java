package edu.ics372.project1.appliancestore.business.entities;

/*
 * @author Cristian Zendejas
 * The Dishwasher subclass for Appliance.
 */
public class Dishwasher extends Appliance {
	//Creates a unique identifier for this subclass
	private static final String DISHWASHER_STRING = "DSHW";

	/*
	 * Creates the Dishwasher object
	 * @returns Dishwasher object
	 */
	public Dishwasher(String brandName, String model, double price, int quantity) {
		super(brandName, model, price, quantity);
		this.setEligibleForBackOrder(true);
	}
	
	@Override
	public String createId() {
		return DISHWASHER_STRING + super.getNextId();
	}
	
	@Override
	public String toString() {
		String output = super.toString();
		return output;
	}

}
