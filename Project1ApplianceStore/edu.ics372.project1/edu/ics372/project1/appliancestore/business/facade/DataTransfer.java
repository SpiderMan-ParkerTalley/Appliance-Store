package edu.ics372.project1.appliancestore.business.facade;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.RepairPlanTransaction;
import edu.ics372.project1.appliancestore.business.entities.SaleTransaction;

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
	private boolean customerHasRepairPlan;
	private String backOrderId;
    private int transactionQuantity;
	private double amountCharged;
	

	public DataTransfer(){
		//TODO reset();
	}


	public double getAmountCharged() {
		return amountCharged;
	}


	public void setAmountCharged(double amountCharged) {
		this.amountCharged = amountCharged;
	}


	public int getTransactionQuantity() {
		return transactionQuantity;
	}

	public void setTransactionQuantity(int transactionQuantity) {
		this.transactionQuantity = transactionQuantity;
	}


	public String getBackOrderId() {
		return backOrderId;
	}

	public void setBackOrderId(String backOrderId) {
		this.backOrderId = backOrderId;
	}

	public int getApplianceType() {
		return applianceType;
	}

	public void setApplianceType(int applianceType) {
		this.applianceType = applianceType;
	}

	public boolean isHasRepairPlan() {
		return customerHasRepairPlan;
	}

	public void setHasRepairPlan(boolean hasRepairPlan) {
		this.customerHasRepairPlan = hasRepairPlan;
	}

	public String getApplianceId() {
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
		customerHasRepairPlan = customer.hasRepairPlan();
	}

	public void setBackOrderFields(BackOrder backOrder) {
		backOrderId = backOrder.getId();
		customerId = backOrder.getCustomer().getId();
		applianceID = backOrder.getAppliance().getId();
		quantity = backOrder.getQuantity();
	};

	
	public void setSaleTransactionFields(SaleTransaction transaction) {
		customerId = transaction.getCustomer().getId();
		applianceID = transaction.getAppliance().getId();
		transactionQuantity = transaction.getQuantity();
	}

	public void setRepairPlanTransactionFields(RepairPlanTransaction transaction) {
		customerId = transaction.getCustomer().getId();
		applianceID = transaction.getAppliance().getId();
	}

	public void setApplianceFields(Appliance appliance){
		applianceID = appliance.getId();
		brandName = appliance.getBrandName();
		modelName = appliance.getModel();
		price = appliance.getPrice();
		quantity = appliance.getQuantity();
	}

	public boolean getCustomerHasRepairPlan() {
		return customerHasRepairPlan;
	}

	public void setCustomerHasRepairPlan(boolean customerHasRepairPlan) {
		this.customerHasRepairPlan = customerHasRepairPlan;
	}

	/**
	 * Resets all fields to empty so there is no overlap or 
	 */

	public void reset() {
		applianceID = "No such appliance ID";
		brandName = "No such appliance brand name";
		modelName = "No such appliance ID";
		price = 0;
		quantity = 0;
		customerId = "No such customer ID";
		customerName = "No such customer name";
		customerAddress = "No such customer address";
		customerPhoneNumber = "No such customer phone number";
		timeStamp = "No such time stamp";
		transactionType = 0;
		applianceType = 0;
		customerHasRepairPlan = false;
		backOrderId = "No such back order ID";
		transactionQuantity = 0;
		amountCharged = 0;
		Request.instance().reset();
	}




}

