package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class KitchenRange extends Appliance {
	private List<KitchenRange> itemsOnStock;
	
	public KitchenRange(String brandName, String model, double price, int quantity, boolean hasRepairPlan) {
		super(brandName, model, price, quantity, hasRepairPlan);
		this.itemsOnStock = new ArrayList<KitchenRange>();
	}

	
}
