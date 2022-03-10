package edu.ics372.project1.appliancestore.business.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import edu.ics372.project1.appliancestore.business.collections.ModelList;

/*
 * @author Cristian Zendejas
 * The parent class for all appliances within the store.
 */
public class Appliance implements Serializable {
	private static final String APPLIANCE_STRING = "APP";
	private static int idCounter = 1;

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
	 * @param quantity int the quantity available of the appliance.
	 */
	public Appliance(String brandName, String model, double price, int quantity) {
		id = createId();
		this.brandName = brandName;
		this.model = model;
		this.price = price;
		this.quantity = quantity;
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Generates a ID for the appliance.
	 * @return String a generated ID.
	 */
	private String createId() {
		return APPLIANCE_STRING + idCounter++;
	}

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
	 * @return boolean true if eligible, false otherwise.
	 */
	public boolean eligibleForRepairPlan() {
		return eligibleForRepairPlan;
	}

	/**
	 * Check if the appliance is eligible for back order plan.
	 * @return boolean true if eligible, false otherwise.
	 */
	public boolean eligibleForBackOrder() {
		return eligibleForBackOrder;
	}

	/*
	 * Returns the quantity of appliances that need to be backordered.
	 * If the quantity of appliances in stock is greater than the quantity
	 * being purchased, needToBackOrder returns 0.
	 * If there is not enough inventory on hand to fulfill the purchase,
	 * needToBackOrder returns the amount needed to back order.
	 * @param int - the quantity being purchased
	 * @return int - The amount of appliances to put on back order. 
	 */
	public int purchase(int quantity) {
		int needToBackOrder = 0;
		if(this.getQuantity() < quantity) {
			needToBackOrder = quantity - this.getQuantity();
			this.setQuantity(0); 
			return needToBackOrder;
		} else {
			this.setQuantity(this.getQuantity()-quantity);
			return needToBackOrder;
		}
	}
	
	public String toString() {
		String output = "Appliance Id: " + id + " Brand: " + brandName + " Model: " + "Price: " + price + " Quantity: " + quantity;
		return output;
	}

} 