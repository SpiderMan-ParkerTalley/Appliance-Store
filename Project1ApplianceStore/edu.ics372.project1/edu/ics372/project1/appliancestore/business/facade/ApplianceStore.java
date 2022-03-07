package edu.ics372.project1.appliancestore.business.facade;

import java.io.Serializable;
import java.util.Iterator;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.RepairPlan;
import edu.ics372.project1.appliancestore.iterators.SafeCustomerIterator;
import edu.ics372.project1.appliancestore.business.collections.CustomerList;
import edu.ics372.project1.appliancestore.business.collections.BackOrderList;
import edu.ics372.project1.appliancestore.business.collections.ModelList;



/**
 * This is the facade class. It handles all requests from users.
 * 
 * @author Jim Sawicki and Sharon Shin
 *
 */
public class ApplianceStore implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ApplianceStore applianceStore;

	private CustomerList customers = CustomerList.getInstance();
	private ModelList models = ModelList.getInstance();
    private BackOrderList backOrders = BackOrderList.getInstance();

	/**
	 * The constructor is private in order to implement the singleton design
	 * pattern.
	 */
	private ApplianceStore() {
	}

	/**
	 * If no singleton has been created, creates a singleton. If a singleton already
	 * exists, returns the singleton.
	 * 
	 * @return A singleton of type ApplianceStore.
	 */
	public ApplianceStore instance() {
		if (applianceStore == null) {
			applianceStore = new ApplianceStore();
        }  
			return applianceStore;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	public Result addModel(Request request) {
		Result result = new Result();
		int type = request.getApplianceType();
		Appliance appliance = ApplianceFactory.createAppliance(type, request);
		if (models.insertModel(appliance)) {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setApplianceFields(appliance);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Organizes the operations for adding a member.
	 * @param customer name
	 * @param customer address
	 * @param customer phone number
	 * @return the Customer objected created
	 */
	public Result addCustomer(Request request) {
		Result result = new Result();
		Customer customer = new Customer(request.getCustomerName(), request.getCustomerAddress(), request.getCustomerPhoneNumber());
		if (customers.insertCustomer(customer)) {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setCustomerFields(customer);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Adds inventory for a single model
	 * @param request
	 * @return result object
	 */
	public Result addInventory(Request request) {
		Result result = new Result();
		models.search(request.getApplianceID()).setQuantity(request.getQuantity());
		result.setResultCode(Result.OPERATION_SUCCESSFUL);
		return result;
	}


	/**
	 * Searches for a given appliance model
	 * @param applianceId of the appliance
	 * @return true iff the appliance is in the model list collection
	 */
	public Result searchModel(Request request) {
		Result result = new Result();
		Appliance appliance = models.search(request.getApplianceID());
		if(appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
		} else {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setApplianceFields(appliance);
		}
		return result;
	}

	/**
	 * Searches for a given customer
	 * @param customerId of the customer
	 * @return true iff the customer is in the customer list collection
	 */
	public Result searchCustomer(Request request){
		Result result = new Result();
		Customer customer = customers.search(request.getCustomerId());
		if(customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
		} else {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setCustomerFields(customer);
		}
		return result;
	}



	/**
	 * This method allows for the purchase for a single appliance type for a single
	 * customer of a variable amount per use case 4. If the quantity of the
	 * appliance requested is not available, then the method processes a purchase of
	 * whatever quantity of appliances is available and generates a backOrder object
	 * of the unfulfilled quantity. This backOrder object is stored in the backOrder
	 * list. Finally, the method returns a Result object with either an error code
	 * or the appropriate fields to verify the purchase of the appliance and/or the
	 * backOrder generated.
	 * 
	 * @param applianceId - Identifies the model and brand of appliance being
	 *                    purchased.
	 * @param CustomerId  - Identifies the customer purchasing the appliance.
	 * @param quantity    - the amount of appliances being purchased.
	 * @return A Result object with the appropriate information.
	 */
	public Result purchaseModel(Request request) {
		Result result = new Result();
        Customer customer = customers.search(request.getCustomerId());
        Appliance appliance = models.search(request.getApplianceID());
        
        if(appliance.getQuantity() >= request.getQuantity()) {
			customer.addTransaction(appliance, request.getQuantity());
			models.search(request.getApplianceID()).setQuantity(appliance.getQuantity() - request.getQuantity());
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
		} else {
			if (appliance.eligibleForBackOrder()) {
				int backOrdersNeeded = request.getQuantity() - appliance.getQuantity();
				if(backOrdersNeeded != request.getQuantity()) {
					customer.addTransaction(appliance, appliance.getQuantity());
					models.search(request.getApplianceID()).setQuantity(0);
				} else {
					BackOrder backOrder = new BackOrder(customer, appliance, backOrdersNeeded);
					backOrders.insertBackOrder(backOrder);
					result.setResultCode(Result.BACKORDER_CREATED);
				}
			} else {
				customer.addTransaction(appliance, appliance.getQuantity());
				models.search(request.getApplianceID()).setQuantity(0);
				result.setResultCode(Result.OPERATION_SUCCESSFUL);
			}
		}
		return result;
	}


    /**
     * Charges all repair plans for all customers. The method acquires an iterator
     * from the customerList and then examines each customer. It grabs a repairPlan iterator
     * from each customer and charges each repair plan it finds. With each repair plan charge,
     * it generates a transaction object and stores it in the customer's transactionList.
     */
    public void chargeRepairPlans() {
        for (Iterator<Customer> customerIterator = customers.iterator(); 
            customerIterator.hasNext();) {
                customerIterator.next().chargeRepairPlans();
        }

    }
}
