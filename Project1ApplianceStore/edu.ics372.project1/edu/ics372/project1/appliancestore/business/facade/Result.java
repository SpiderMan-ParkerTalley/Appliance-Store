package edu.ics372.project1.appliancestore.business.facade;

import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;

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
    public static final int BACKORDER_CREATED = 6;
    public static final int NOT_A_VALID_QUANTITY = 7;
	public static final int REPAIR_PLAN_NOT_FOUND = 8;
	public static final int NOT_ELIGABLE_FOR_REPAIR_PLAN = 9;

	private List<Customer> customers;
	private List<BackOrder> backOrders;
	private int resultCode;

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


}

