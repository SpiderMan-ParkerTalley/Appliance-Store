package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class KitchenRange extends Appliance {
	private List<KitchenRange> itemsOnStock;
	
	public KitchenRange(String brandName, String model, double price, int quantity) {
		super(brandName, model, price, quantity);
		this.itemsOnStock = new ArrayList<KitchenRange>();
		this.setEligibleForBackOrder(true);
	}

	@Override
	public String toString() {
		String output = super.toString();
		return output;
	}
	
}
