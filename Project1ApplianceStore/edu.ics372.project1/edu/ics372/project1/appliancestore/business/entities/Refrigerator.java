package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Refrigerator extends Appliance{
	private double capacity;
	private List<Refrigerator> itemsOnStock;
	
	public Refrigerator(String brandName, String model, double price, int quantity, boolean hasRepairPlan, double capacity) {
		super(brandName, model, price, quantity, hasRepairPlan);
		this.capacity = capacity;
		this.itemsOnStock = new ArrayList<Refrigerator>();
	}
	
}
