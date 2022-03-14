package edu.ics372.project1.appliancestore.business.facade;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.RepairPlan;

import java.util.List;

/**
 * Ferries the results of operations in the facade to the UI.
 * 
 * @author Jim Sawicki and Sharon Shin
 *
 */
public class Result extends DataTransfer {

	public static final int APPLIANCE_NOT_FOUND = 1;
	public static final int CUSTOMER_NOT_FOUND = 2;
	public static final int BACK_ORDER_NOT_FOUND = 3;
	public static final int OPERATION_SUCCESSFUL = 4;
	public static final int OPERATION_FAILED = 5;
    public static final int BACK_ORDER_CREATED = 6;
    public static final int NOT_A_VALID_QUANTITY = 7;
	public static final int REPAIR_PLAN_NOT_FOUND = 8;
	public static final int NOT_ELIGIBLE_FOR_REPAIR_PLAN = 9;
	public static final int REPAIR_PLAN_ENROLLED = 10;
	public static final int CUSTOMER_HAS_NOT_PURCHASED_APPLIANCE = 11;
	public static final int PARTIAL_FULFILLMENT = 12;

	private List<Customer> customers;
	private List<BackOrder> backOrders;
	private int resultCode;
	private double totalRevenueFromTransactions;
	private double totalRevenueFromRepairPlans;
	private List<Appliance> appliances;

	public List<Appliance> getAppliances() {
		return appliances;
	}

	public void setAppliances(List<Appliance> appliances) {
		this.appliances = appliances;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<BackOrder> getBackOrders() {
		return backOrders;
	}

	public void setBackOrders(List<BackOrder> backOrders) {
		this.backOrders = backOrders;
	}

	public double getTotalRevenueFromTransactions() {
		return totalRevenueFromTransactions;
	}

	public void setTotalRevenueFromTransactions(double totalRevenueFromTransactions) {
		this.totalRevenueFromTransactions = totalRevenueFromTransactions;
	}

	public double getTotalRevenueFromRepairPlans() {
		return totalRevenueFromRepairPlans;
	}

	public void setTotalRevenueFromRepairPlans(double totalRevenueFromRepairPlans) {
		this.totalRevenueFromRepairPlans = totalRevenueFromRepairPlans;
	}

	/**
	 * Sets the fields necessary for the implementation of use case 11.
	 * @param next The repairPlan in question
	 */
	public void setRepairPlanFields(RepairPlan next) {
		setCustomerFields(next.getCustomer());
		setApplianceFields(next.getAppliance());
		setHasRepairPlan(next.getCustomer().hasRepairPlan());
	}

}

