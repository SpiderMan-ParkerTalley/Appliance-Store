package edu.ics372.project1.appliancestore.business.entities;

/**
 * Refrigerator class for creating refrigerator objects.
 * 
 * @author Cristian Zendejas and Parker Talley
 */
public class Refrigerator extends Appliance{
	/**
	 * Stores the refrigerator ID code.
	 */
	private static final String REFRIGERATOR_STRING = "REFR";
	
	/**
	 * Stores the refrigerator internal capacity.
	 */
	private double capacity;
	
	/**
	 * Creates refrigerator object.
	 * 
	 * @param brandName String brand name of the refrigerator.
	 * @param model String model of the refrigerator.
	 * @param price double price of the refrigerator.
	 * @param capacity double internal capacity of the refrigerator.
	 */
	public Refrigerator(String brandName, String model, double price, double capacity) {
		super(brandName, model, price);
		this.capacity = capacity;
		this.setEligibleForBackOrder(true);
	}
	
	@Override
	public String createId() {
		return REFRIGERATOR_STRING + super.getNextId();
	}
	
	// Getters
	public double getCapacity() {
		return capacity;
	}

	// Setters
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public String toString() {
		String output = super.toString() + " Capacity: " + capacity;
		return output;
	}

}
