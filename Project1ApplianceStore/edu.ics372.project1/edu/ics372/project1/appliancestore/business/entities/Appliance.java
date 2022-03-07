package edu.ics372.project1.appliancestore.business.entities;

import java.sql.Timestamp;

public class Appliance {
	private int applianceId;
	private String brandName;
	private String model;
	private double price;
	private int quantity;
	private Timestamp createdAt;
	private boolean hasRepairPlan;
	private int idCounter = 0;
	
	public Appliance(String brandName, String model, double price, int quantity) {
		this.applianceId = createId();
		this.brandName = brandName;
		this.model = model;
		this.price = price;
		this.quantity = quantity;
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}

	private int createId() {
		idCounter++;
		return idCounter;
	}

	public int getApplianceId() {
		return applianceId;
	}

	public void setApplianceId(int applianceId) {
		this.applianceId = applianceId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isHasRepairPlan() {
		return hasRepairPlan;
	}

	public void setHasRepairPlan(boolean hasRepairPlan) {
		this.hasRepairPlan = hasRepairPlan;
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
	
	/*
	 * Returns a boolean if the appliance is eligible for a repair plan
	 * @params none
	 * @returns boolean
	 */
	public boolean eligibleRepairPlan() {
		System.out.println("the check for the repair plan works!");
		return false;
	}
	
}