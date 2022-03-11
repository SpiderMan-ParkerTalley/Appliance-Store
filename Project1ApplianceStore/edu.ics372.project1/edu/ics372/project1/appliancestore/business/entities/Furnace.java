package edu.ics372.project1.appliancestore.business.entities;

public class Furnace extends Appliance {
	private double maxHeatingOutput;
	
	public Furnace(String brandName, String model, double price, int quantity, double maxHeatingOutput) {
		super(brandName, model, price, quantity);
		this.maxHeatingOutput = maxHeatingOutput;
	}

	public double getMaxHeatingOutput() {
		return maxHeatingOutput;
	}

	public void setMaxHeatingOutput(double maxHeatingOutput) {
		this.maxHeatingOutput = maxHeatingOutput;
	}

}
