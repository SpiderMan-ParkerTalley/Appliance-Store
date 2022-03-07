package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Dishwasher extends Appliance {
	private List<Dishwasher> itemsOnStock;
	
	public Dishwasher(String brandName, String model, double price, int quantity) {
		super(brandName, model, price, quantity);
		this.itemsOnStock = new ArrayList<Dishwasher>();
		this.setElilgibleForBackOrder(true);
	}


}
