package edu.ics372.project1.appliancestore.business.entities;

public class Dishwasher extends Appliance {
	private static final String DISHWASHER_STRING = "DSHW";

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
