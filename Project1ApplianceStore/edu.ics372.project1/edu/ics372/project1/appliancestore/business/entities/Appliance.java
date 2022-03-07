package edu.ics372.project1.appliancestore.business.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Appliance implements Serializable {
	private static final String APPLIANCE_STRING = "APP";
	private String id;
	private String brandName;
	private String model;
	private double price;
	private int quantity;
	private Timestamp createdAt;
	private boolean eligibleForBackOrder = false;
	private boolean eligibleForRepairPlan = false;
	private int idCounter = 0;

	public Appliance(String brandName, String model, double price, int quantity) {
		id = createId();
		this.brandName = brandName;
		this.model = model;
		this.price = price;
		this.quantity = quantity;
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

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

	public void setElilgibleForRepairPlan(boolean eligibleForRepairPlan) {
		this.eligibleForRepairPlan = eligibleForRepairPlan;
	}

	public void setElilgibleForBackOrder(boolean eligibleForBackOrder) {
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