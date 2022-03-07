package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Washer extends Appliance {
	private double repairPlanAmount;
	private List<Washer> itemsOnStock;

	public Washer(String brandName, String model, double price, int quantity, boolean hasRepairPlan, double repairPlanAmount) {
		super(brandName, model, price, quantity, hasRepairPlan);
		this.repairPlanAmount = repairPlanAmount;
		this.itemsOnStock = new ArrayList<Washer>();
	}
}
