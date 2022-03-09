package edu.ics372.project1.appliancestore.business.facade;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.RepairPlan;
import edu.ics372.project1.appliancestore.business.entities.Transaction;
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
    private BackOrderList backorders = BackOrderList.getInstance();

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
		Appliance appliance = models.search(request.getApplianceID());
		result.setApplianceFields(appliance);
		result.setResultCode(Result.OPERATION_SUCCESSFUL);
		return result;
	}

	/**
	 * Fulfills backorders, given the inventory has enough to do so. If not, it will return a
	 * result code stating that is not able to do so.
	 * @param request
	 * @return
	 */
	public Result fulfillBackorder(Request request) {
		Result result = new Result();
		BackOrder backorder = backorders.search(request.getBackorderId());
		if(backorder == null) {
			result.setResultCode(Result.BACK_ORDER_NOT_FOUND);
		} else {
			if(backorder.getQuantity() > models.search(backorder.getAppliance().getId()).getQuantity()) {
				result.setResultCode(Result.NOT_A_VALID_QUANTITY);
			} else {
				Transaction transaction = new Transaction(backorder.getCustomer(), backorder.getAppliance(), backorder.getQuantity());
				if(backorder.getCustomer().addTransaction(transaction)) {
					result.setBackOrderFields(backorder);
					result.setCustomerFields(backorder.getCustomer());
					backorders.removeBackOrder(backorder);
					result.setTransactionFields(transaction);
					int newQuantity = models.search(backorder.getAppliance().getId()).getQuantity() - backorder.getQuantity();
					models.search(backorder.getAppliance().getId()).setQuantity(newQuantity);
					Appliance appliance = models.search(backorder.getAppliance().getId());
					result.setApplianceFields(appliance);
					result.setResultCode(Result.OPERATION_SUCCESSFUL);
				}
			}
		}
		return result;
	}

	/**
	 * Enrolls a customer into a repair plan. If the customer sucessfully enrolls, it returns a 
	 * success operation code, and if not, it returns a failed operation code. 
	 * @param request
	 * @return
	 */
	public Result enrollRepairPlan(Request request) {
		Result result = new Result();
		Appliance appliance = models.search(request.getApplianceID());
		if(appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		} else if (appliance.eligibleForRepairPlan() == false) {
			result.setResultCode(Result.NOT_ELIGABLE_FOR_REPAIR_PLAN);
			return result;
		}
		result.setApplianceFields(appliance);
		Customer customer = customers.search(request.getCustomerId());
		if(customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		}
		result.setCustomerFields(customer);
		if(models.search(request.getApplianceID()).eligibleForRepairPlan()) {
			if(customers.search(request.getCustomerId()).addRepairPlan(models.search(request.getApplianceID()))) {
				result.setResultCode(Result.OPERATION_SUCCESSFUL);
				return result;
			}
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Withdraws a customer from a repair plan for a single model. If the customer withdraws,
	 * it returns a success operation code, if something goes wrong, it returns the associated
	 * error code.
	 * @param request
	 * @return
	 */
	public Result withdrawRepairPlan(Request request) { 
		Result result = new Result();
		Customer customer = customers.search(request.getCustomerId());
		if(customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		}
		result.setCustomerFields(customer);
		Appliance appliance = models.search(result.getApplianceID());
		if(appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		}
		result.setApplianceFields(appliance);
		RepairPlan repairPlan = customers.search(request.getCustomerId()).searchRepairPlan(request.getCustomerId(), request.getApplianceID()); 
		if(repairPlan == null) {
			result.setResultCode(Result.REPAIR_PLAN_NOT_FOUND);
			return result;
		}
		//Do we want to add repairPlan info and Backorder info to result that we return?
		if(customer.removeRepairPlan(repairPlan)) {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}
	
	/**
	 * Lists all models of a single appliance or all appliances based on user input. 
	 * If there are no such models, it returns an error code, but if there are models,
	 * it returns the list of models.
	 * @param request
	 * @return
	 */
	public Result listAppliances(Request request) {
		Result result = new Result();
		List<Appliance> appliances = new LinkedList<Appliance>();
		if(request.getApplianceType() == 7) {
			for(Appliance model : models) {
				appliances.add(model);
			}
		} else {
			Appliance appliance = ApplianceFactory.findApplianceType(request.getApplianceType());
			for(Appliance model : models) {
				if(model.getClass().equals(appliance.getClass())) {
					appliances.add(model);
				}
			}
		}
		if(appliances.isEmpty()) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
			}
		result.setAppliances(appliances);
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
	 * Searches for a given backorder
	 * @param backorderId of the backorder
	 * @return true iff the backorder is in the backorder list collection
	 */
	public Result searchBackorder(Request request) {
		Result result = new Result();
		BackOrder backorder = backorders.search(request.getBackorderId());
		if(backorder == null) {
			result.setResultCode(Result.BACK_ORDER_NOT_FOUND);
		} else {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setBackOrderFields(backorder);
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

	 //TODO change for addTransactions
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
					backorders.insertBackOrder(backOrder);
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

