package edu.ics372.project1.appliancestore.business.entities;

public class Refrigerator extends Appliance{
	private double capacity;
	
	public Refrigerator(String brandName, String model, double price, int quantity, double capacity) {
		super(brandName, model, price, quantity);
		this.capacity = capacity;
		this.setEligibleForBackOrder(true);
	}
	
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		String output = super.toString() + " Capacity: " + capacity;
		return output;
	}

}
