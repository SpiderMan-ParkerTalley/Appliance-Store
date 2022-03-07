package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Dryer extends Appliance {
	private double repairPlanAmount;
	private List<Washer> itemsOnStock;

	public Dryer(String brandName, String model, double price, int quantity, boolean hasRepairPlan, double repairPlanAmount) {
		super(brandName, model, price, quantity, hasRepairPlan);
		this.repairPlanAmount = repairPlanAmount;
		this.itemsOnStock = new ArrayList<Washer>();
	}
}
