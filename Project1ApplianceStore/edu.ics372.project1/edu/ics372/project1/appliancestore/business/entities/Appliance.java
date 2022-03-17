package edu.ics372.project1.appliancestore.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Abstract parent class for all appliances within the store.
 * 
 * @author Cristian Zendejas and Parker Talley.
 */
public abstract class Appliance implements Serializable {
	/**
	 * Stores the next ID for appliance/model.
	 */
	protected static int nextId = 0;
	
	/**
	 * Stores the appliance identification number.
	 */
	private String id;

	/**
	 * Stores the appliance brand name.
	 */
	private String brandName;

	/**
	 * Stores the appliance model.
	 */
	private String model;

	/**
	 * Stores the appliance price.
	 */
	private double price;

	/**
	 * Stores the appliance quantity available.
	 */
	private int quantity;

	/**
	 * Stores date/time the appliance was added to the system.
	 */
	private Timestamp createdAt;

	/**
	 * Stores the appliance eligibility for back order.
	 */
	private boolean eligibleForBackOrder = false;

	/**
	 * Stores the appliance eligibility for repair plan.
	 */
	private boolean eligibleForRepairPlan = false;

	/**
	 * Constructor for appliance.
	 * @param brandName String the brand name of the appliance.
	 * @param model String the model of the appliance.
	 * @param price double the price of the appliance.
	 */
	public Appliance(String brandName, String model, double price) {
		id = createId();
		this.brandName = brandName;
		this.model = model;
		this.price = price;
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Generates a generic string for the appliance.
	 * 
	 * @return a generated ID.
	 */
	public abstract String createId();

	// Setters
	public void setId(String applianceId) {
		id = applianceId;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public void setEligibleForRepairPlan(boolean eligibleForRepairPlan) {
		this.eligibleForRepairPlan = eligibleForRepairPlan;
	}

	public void setEligibleForBackOrder(boolean eligibleForBackOrder) {
		this.eligibleForBackOrder = eligibleForBackOrder;
	}

	// Getters
	public String getId() {
		return id;
	}
	
	public int getNextId() {
		return ++nextId;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getModel() {
		return model;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	/**
	 * Check if the appliance is eligible for repair plan.
	 * 
	 * @return boolean true if eligible, false otherwise.
	 */
	public boolean eligibleForRepairPlan() {
		return eligibleForRepairPlan;
	}

	/**
	 * Check if the appliance is eligible for back order plan.
	 * 
	 * @return boolean true if eligible, false otherwise.
	 */
	public boolean eligibleForBackOrder() {
		return eligibleForBackOrder;
	}

	/**
	 * Determines the new quantity and back order quantity (if any) when an 
	 * appliance is purchased.
	 * 
	 * @param int quantity the quantity being purchased
	 * @return int the amount of appliances to put on back order. 
	 */
	public int purchase(int quantity) {
		int needToBackOrder = 0;
		if(this.getQuantity() < quantity) {
			needToBackOrder = quantity - this.getQuantity();
			this.setQuantity(0); 
			return needToBackOrder;
		} else {
			this.setQuantity(this.getQuantity() - quantity);
			return needToBackOrder;
		}
	}

	 /**
     * Saves the static fields in Appliance class.
     * 
     * @param output ObjectOutputStream object.
     */
    public static void save(ObjectOutputStream output) throws IOException {
        output.writeObject(nextId);
    }
    /**
    * Retrieves the static fields in Appliance class.
    */
    public static void retrieve(ObjectInputStream input) throws IOException, 
        ClassNotFoundException {
    		nextId = (int) input.readObject();
    }
	
	/**
	 * String form of appliance.
	 */
	public String toString() {
		String output = this.getClass().getSimpleName() + " id: " + this.getId() + 
			" Brand: " + brandName + " Model: " + "Price: " + price + 
			" Quantity: " + quantity;
		return output;
	}
} 