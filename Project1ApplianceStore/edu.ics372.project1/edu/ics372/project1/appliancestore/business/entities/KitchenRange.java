package edu.ics372.project1.appliancestore.business.entities;

/**
 * KitchenRange class for creating kitchen range objects.
 * 
 * @author Cristian Zendejas and Parker Talley.
 */
public class KitchenRange extends Appliance {
	/**
	 * Stores the kitchen range ID code.
	 */
	private static final String KITCHEN_STRING = "KIT";
	
	/**
	 * Creates kitchen range object.
	 * 
	 * @param brandName String brand name of the kitchen range.
	 * @param model String model of the kitchen range.
	 * @param price double price of the kitchen range.
	 */
	public KitchenRange(String brandName, String model, double price) {
		super(brandName, model, price);
		this.setEligibleForBackOrder(true);
	}
	
	@Override
	public String createId() {
		return KITCHEN_STRING + super.getNextId();
	}

	@Override
	public String toString() {
		String output = super.toString();
		return output;
	}
	
}
