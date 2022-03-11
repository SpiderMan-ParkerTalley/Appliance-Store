package edu.ics372.project1.appliancestore.business.entities;

public class Furnace extends Appliance {
	private static final String FURNACE_STRING = "FURN";
	private double maxHeatingOutput;
	
	public Furnace(String brandName, String model, double price, int quantity, double maxHeatingOutput) {
		super(brandName, model, price, quantity);
		this.maxHeatingOutput = maxHeatingOutput;
	}
	
	@Override
	public String createId() {
		return FURNACE_STRING + super.getNextId();
	}

	public double getMaxHeatingOutput() {
		return maxHeatingOutput;
	}

	public void setMaxHeatingOutput(double maxHeatingOutput) {
		this.maxHeatingOutput = maxHeatingOutput;
	}

}
