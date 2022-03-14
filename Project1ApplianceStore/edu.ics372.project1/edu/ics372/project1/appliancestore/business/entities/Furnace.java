package edu.ics372.project1.appliancestore.business.entities;

/*
 * @author Cristian Zendejas
 * The Furnace subclass for Appliance.
 */
public class Furnace extends Appliance {
	//Creates a unique identifier for this subclass
	private static final String FURNACE_STRING = "FURN";
	
	//Stores the numerical value for the heating output
	private double maxHeatingOutput;
	
	/*
	 * Creates a Furance object
	 * @returns Furnace object
	 */
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
