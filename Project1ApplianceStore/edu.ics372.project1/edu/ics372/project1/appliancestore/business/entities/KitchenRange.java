package edu.ics372.project1.appliancestore.business.entities;

/*
 * @author Cristian Zendejas
 * The subclass for Appliance for kitchen ranges. 
 */
public class KitchenRange extends Appliance {
	//Creates a unique identifier for this subclass
	private static final String KITCHEN_STRING = "KIT";
	
	/*
	 * Creates a KitchenRange object for the store.
	 * @return KitchenRange
	 */
	public KitchenRange(String brandName, String model, double price, int quantity) {
		super(brandName, model, price, quantity);
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
