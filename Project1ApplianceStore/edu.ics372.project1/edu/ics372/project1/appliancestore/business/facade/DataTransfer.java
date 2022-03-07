package edu.ics372.project1.appliancestore.business.facade;

/**
 * Superclass for Result and Request. Provides a safe way to share data between
 * the UI and the Facade.
 * 
 * @author Jim Sawicki and Sharon Shin
 *
 */
public abstract class DataTransfer {
	private String applianceID;
	private String brandName;
	private String modelName;
	private double price;
	private int quantity;
	private String customerId;
//	private Timestamp createdAt; // Not sure if this the right type? Do we want a gregorian calendar?

	public String getApplianceID() {
		return applianceID;
	}

	public void setApplianceID(String applianceID) {
		this.applianceID = applianceID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}

