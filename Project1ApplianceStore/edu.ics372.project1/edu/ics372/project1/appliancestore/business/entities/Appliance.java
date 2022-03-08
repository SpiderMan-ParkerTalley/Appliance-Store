package edu.ics372.project1.appliancestore.business.entities;

import java.io.Serializable;
import java.sql.Timestamp;

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
	 * Returns the amount of appliances purchased and which ones were backorders?
	 * @params quantity of desired appliances
	 * @returns integer 
	 */
	public int purchase(int quantity) {
		System.out.println("the purchase function runs!");
		return 0;
	}
} 