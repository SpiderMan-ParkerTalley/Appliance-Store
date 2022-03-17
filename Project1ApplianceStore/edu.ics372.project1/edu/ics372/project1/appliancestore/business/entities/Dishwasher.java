package edu.ics372.project1.appliancestore.business.entities;

/**
 * Dishwasher class for creating dishwasher objects.
 * 
 * @author Cristian Zendejas and Parker Talley
 */
public class Dishwasher extends Appliance {
	/**
	 * Stores the dishwasher ID code.
	 */
	private static final String DISHWASHER_STRING = "DSHW";

	/**
	 * Creates dishwasher object.
	 * 
	 * @param brandName String brand name of the dishwasher.
	 * @param model String model of the dishwasher.
	 * @param price double price of the dishwasher.
	 * @param quantity int quantity of the dishwasher.
	 */
	public Dishwasher(String brandName, String model, double price) {
		super(brandName, model, price);
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
