package edu.ics372.project1.appliancestore.business.entities;

public class KitchenRange extends Appliance {
	
	public KitchenRange(String brandName, String model, double price, int quantity) {
		super(brandName, model, price, quantity);
		this.setEligibleForBackOrder(true);
	}

	@Override
	public String toString() {
		String output = super.toString();
		return output;
	}
	
}
