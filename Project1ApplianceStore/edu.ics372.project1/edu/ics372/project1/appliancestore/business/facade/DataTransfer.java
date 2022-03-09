package edu.ics372.project1.appliancestore.business.facade;

import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.Transaction;

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
	private String customerName;
	private String customerAddress;
	private String customerPhoneNumber;
	private String timeStamp;
	private int transactionType;
	private int applianceType;
	private boolean hasRepairPlan;
	private String backorderId;
    private int transactionQuantity;
	//Safe way to list appliances? Maybe put in Result? Does Request have a use for this?
	private List<Appliance> appliances;
	

	public DataTransfer(){
		reset();
	}

	public List<Appliance> getAppliances() {
		return appliances;
	}

	public void setAppliances(List<Appliance> appliances) {
		this.appliances = appliances;
	}

	public int getTransactionQuantity() {
		return transactionQuantity;
	}

	public void setTransactionQuantity(int transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
	}


	public String getBackorderId() {
		return backorderId;
	}

	public void setBackorderId(String backorderId) {
		this.backorderId = backorderId;
	}

	public int getApplianceType() {
		return applianceType;
	}

	public void setApplianceType(int applianceType) {
		this.applianceType = applianceType;
	}

	public boolean isHasRepairPlan() {
		return hasRepairPlan;
	}

	public void setHasRepairPlan(boolean hasRepairPlan) {
		this.hasRepairPlan = hasRepairPlan;
	}

	public String getApplianceID() {
		return applianceID;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String phoneNumber) {
		this.customerPhoneNumber = phoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String address) {
		this.customerAddress = address;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String name) {
		this.customerName = name;
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

	public void setCustomerFields(Customer customer) {
		customerId = customer.getId();
		customerName = customer.getName();
		customerPhoneNumber = customer.getPhoneNumber();
		customerAddress = customer.getAddress();
	}

	public void setBackOrderFields(BackOrder backorder) {
		backorderId = backorder.getId();
	};

	
	public void setTransactionFields(Transaction transaction) {
		customerId = transaction.getCustomer().getId();
		applianceID = transaction.getAppliance().getId();
		transactionQuantity = transaction.getQuantity();
	}

	public void setApplianceFields(Appliance appliance){
		//TBD
	}

	public void reset() {
		applianceID = "Invalid appliance id";
		modelName = "No such model";
		price = 0;
		timeStamp = "Not applicable (not processed)";
		brandName = "No such brand";
		customerId = "Invalid customer id";
		customerName = "No such customer";
		customerPhoneNumber = "No such customer";
		customerAddress = "No such customer";
		quantity = 0;
	}



}

