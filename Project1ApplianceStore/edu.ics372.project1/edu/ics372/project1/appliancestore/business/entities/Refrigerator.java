package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Refrigerator extends Appliance{
	private double capacity;
	private List<Refrigerator> itemsOnStock;
	
	public Refrigerator(String brandName, String model, double price, int quantity, double capacity) {
		super(brandName, model, price, quantity);
		this.capacity = capacity;
		this.itemsOnStock = new ArrayList<Refrigerator>();
		this.setEligibleForBackOrder(true);
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

}
