package edu.ics372.project1.appliancestore.business.entities;

/**
 * The Furnace subclass for Appliance.
 * 
 * @author Cristian Zendejas and Parker Talley
 */
public class Furnace extends Appliance {
	/**
	 * Stores the furnace ID code.
	 */
	private static final String FURNACE_STRING = "FURN";
	
	/**
	 * Stores the maximum heating output of the furnace.
	 */
	private double maxHeatingOutput;
	
	/**
	 * Creates furnace object.
	 * 
	 * @param brandName String brand name of the furnace.
	 * @param model String model of the furnace.
	 * @param price double price of the furnace.
	 * @param maxHeatingOutput double max heating output of furnace.
	 */
	public Furnace(String brandName, String model, double price, double maxHeatingOutput) {
		super(brandName, model, price);
		this.maxHeatingOutput = maxHeatingOutput;
	}
	
	@Override
	public String createId() {
		return FURNACE_STRING + super.getNextId();
	}

	// Getter
	public double getMaxHeatingOutput() {
		return maxHeatingOutput;
	}

	// Setter
	public void setMaxHeatingOutput(double maxHeatingOutput) {
		this.maxHeatingOutput = maxHeatingOutput;
	}

}
