package edu.ics372.project1.appliancestore.business.entities;

/*
 * @author Cristian Zendejas
 * The refrigerator subclass for Appliance.
 */
public class Refrigerator extends Appliance{
	//Creates a unique identifier for this subclass
	private static final String REFRIGERATOR_STRING = "REFR";
	//Stores the numerical value for the capacity of the refrigerator.
	private double capacity;
	
	
	/*
	 * Creates a refrigerator object
	 * @return Refrigerator object
	 */
	public Refrigerator(String brandName, String model, double price, int quantity, double capacity) {
		super(brandName, model, price, quantity);
		this.capacity = capacity;
		this.setEligibleForBackOrder(true);
	}
	
	@Override
	public String createId() {
		return REFRIGERATOR_STRING + super.getNextId();
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
