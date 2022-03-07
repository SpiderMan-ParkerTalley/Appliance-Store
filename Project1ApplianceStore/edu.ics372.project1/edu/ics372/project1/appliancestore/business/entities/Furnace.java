package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Furnace extends Appliance {
	private double maxHeatingOuput;
	private List<Furnace> itemsOnStock;
	
	public Furnace(String brandName, String model, double price, int quantity, boolean hasRepairPlan, double maxHeatingOuput) {
		super(brandName, model, price, quantity, hasRepairPlan);
		this.itemsOnStock = new ArrayList<Furnace>();
	}
	
}
