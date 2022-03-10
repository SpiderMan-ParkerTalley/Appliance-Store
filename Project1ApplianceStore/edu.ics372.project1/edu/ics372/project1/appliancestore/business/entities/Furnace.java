package edu.ics372.project1.appliancestore.business.entities;

import java.util.ArrayList;
import java.util.List;

public class Furnace extends Appliance {
	private double maxHeatingOutput;
	private List<Furnace> itemsOnStock;
	
	public Furnace(String brandName, String model, double price, int quantity, double maxHeatingOutput) {
		super(brandName, model, price, quantity);
		this.maxHeatingOutput = maxHeatingOutput;
		this.itemsOnStock = new ArrayList<Furnace>();
	}

	public double getMaxHeatingOutput() {
		return maxHeatingOutput;
	}

	public void setMaxHeatingOutput(double maxHeatingOutput) {
		this.maxHeatingOutput = maxHeatingOutput;
	}

}
