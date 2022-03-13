package edu.ics372.project1.appliancestore.business.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.RepairPlan;
import edu.ics372.project1.appliancestore.business.entities.Transaction;
import edu.ics372.project1.appliancestore.business.iterators.FilteredApplianceIterator;
import edu.ics372.project1.appliancestore.business.iterators.SafeApplianceIterator;
import edu.ics372.project1.appliancestore.business.iterators.SafeBackOrderIterator;
import edu.ics372.project1.appliancestore.business.iterators.SafeCustomerIterator;
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
	 * @return A singleton of type ApplianceStore.
	 */
	public static ApplianceStore instance() {
		if (applianceStore == null) {
			return applianceStore = new ApplianceStore();
		} else {
			return applianceStore;
		}
	}

	/**
	 * Removes all data from appliance store.
	 */
	public static void clear() {
		ApplianceStore.instance().customers.clear();
		ApplianceStore.instance().models.clear();
		ApplianceStore.instance().backOrders.clear();
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
	 * 
	 * @param customer name
	 * @param customer address
	 * @param customer phone number
	 * @return the Customer objected created
	 */
	public Result addCustomer(Request request) {
		Result result = new Result();
		Customer customer = new Customer(request.getCustomerName(), request.getCustomerAddress(),
				request.getCustomerPhoneNumber());
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
	 * 
	 * @param request
	 * @return result object
	 */
	public Result addInventory(Request request) {
		Result result = new Result();
		models.search(request.getApplianceId()).setQuantity(request.getQuantity());
		Appliance appliance = models.search(request.getApplianceId());
		result.setApplianceFields(appliance);
		result.setResultCode(Result.OPERATION_SUCCESSFUL);
		return result;
	}

	/**
	 * Fulfills back orders, given the inventory has enough to do so. If not, it will
	 * return a result code stating that is not able to do so.
	 * 
	 * @param request
	 * @return
	 */
	public Result fulfillBackOrder(Request request) {
		Result result = new Result();
		BackOrder backOrder = backOrders.search(request.getBackOrderId());
		if (backOrder == null) {
			result.setResultCode(Result.BACK_ORDER_NOT_FOUND);
		} else {
			if (backOrder.getQuantity() > models.search(backOrder.getAppliance().getId()).getQuantity()) {
				result.setResultCode(Result.NOT_A_VALID_QUANTITY);
			} else {
				Transaction transaction = new Transaction(backOrder.getCustomer(), backOrder.getAppliance(),
						backOrder.getQuantity());
				if (backOrder.getCustomer().addTransaction(transaction)) {
					result.setBackOrderFields(backOrder);
					result.setCustomerFields(backOrder.getCustomer());
					backOrders.removeBackOrder(backOrder);
					result.setTransactionFields(transaction);
					int newQuantity = models.search(backOrder.getAppliance().getId()).getQuantity()
							- backOrder.getQuantity();
					models.search(backOrder.getAppliance().getId()).setQuantity(newQuantity);
					Appliance appliance = models.search(backOrder.getAppliance().getId());
					result.setApplianceFields(appliance);
					result.setResultCode(Result.OPERATION_SUCCESSFUL);
				}
			}
		}
		return result;
	}

	/**
	 * Enrolls a customer into a repair plan. If the customer successfully enrolls,
	 * it returns a success operation code, and if not, it returns a failed
	 * operation code.
	 * 
	 * @param request
	 * @return
	 */
	public Result enrollRepairPlan(Request request) {
		Result result = new Result();
		
		// Check if customer exist in system.
		Customer customer = customers.search(request.getCustomerId());
		if (customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		}

		// Check if appliance exist in system.
		Appliance appliance = models.search(request.getApplianceId());
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		} 

		// Check if appliance is eligible for repair plan.
		if (appliance.eligibleForRepairPlan() == false) {
			result.setResultCode(Result.NOT_ELIGIBLE_FOR_REPAIR_PLAN);
			return result;
		}
		result.setCustomerFields(customer);
		result.setApplianceFields(appliance);
		result.setResultCode(Result.CUSTOMER_HAS_NOT_PURCHASED_APPLIANCE); // default if appliance not found in transactions
		// Validating that customer has purchased this appliance. If true, enroll
		Iterator<Transaction> transactionIterator = customer.getTransactionIterator();
		while(transactionIterator.hasNext()) {
			Transaction transaction = transactionIterator.next();
			if (transaction.getAppliance().getId().equals(appliance.getId())) {
				customer.addRepairPlan(appliance);
				result.setResultCode(Result.OPERATION_SUCCESSFUL);
				return result;
			}
		}
		return result;
	}

	/**
	 * Withdraws a customer from a repair plan for a single model. 
	 * 
	 * @param request Request contains customer ID and appliance ID.
	 * @param String customerId the customer's ID.
	 * @param String applianceId the appliance's ID.
	 * @return Result result containing result code AND repair plan if operation
	 * was successful.
	 */
	public Result withdrawRepairPlan(Request request) {
		Result result = new Result();
		Customer customer = customers.search(request.getCustomerId());
		if (customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		}
		result.setCustomerFields(customer);
		Appliance appliance = models.search(result.getApplianceId());
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		}
		result.setApplianceFields(appliance);
		RepairPlan repairPlan = customers.search(request.getCustomerId()).searchRepairPlan(request.getApplianceId());
		if (repairPlan == null) {
			result.setResultCode(Result.REPAIR_PLAN_NOT_FOUND);
			return result;
		}
		// Do we want to add repairPlan info and back order info to result that we
		// return?
		if (customer.removeRepairPlan(repairPlan)) {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}


	/**
	 * Retrieves an iterator for the appliances requested. This could be all
	 * appliances or a select category.
	 * @param applianceType int appliance category code.
	 * @return Iterator<Result> iterator of appliances.
	 */
	public Iterator<Result> listAppliances(Request request) {
		// Returns iterator for all appliance(s)
		if (request.getApplianceType() == 7) {
			return new SafeApplianceIterator(models.iterator());
		} 
		// Returns an iterator for a select category of appliances.
		String applianceCode = ApplianceFactory.findApplianceType(request.getApplianceType());
		return new SafeApplianceIterator(new FilteredApplianceIterator(ModelList.getInstance().iterator(), applianceCode));
	}


	/**
	 * Searches for a given appliance model and returns the result.
     * Returns the appliance id, brand name, model name, price, and
     * quantity in a Result object.
     * If the appliance is not found, returns an error code.
	 * 
	 * @param applianceId String - appliance id.
	 * @return a code representing the outcome AND appliance information.
	 */
	public Result searchModel(Request request) {
		Result result = new Result();
		Appliance appliance = models.search(request.getApplianceId());
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
		} else {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setApplianceFields(appliance);
		}
		return result;
	}

	/**
	 * Determines if a customer exist with a given customer id.
	 * @param customerId String - customer id.
	 * @return Result - a code representing the outcome AND customer information.
	 */
	public Result searchCustomer(Request request) {
		Result result = new Result();
		Customer customer = customers.search(request.getCustomerId());
		if (customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
		} else {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setCustomerFields(customer);
		}
		return result;
	}

	/**
	 * Searches for a given back order.
	 * @param backOrderId String - the back order id.
	 * @return Result - a code representing the outcome AND back order information.
	 */
	public Result searchBackOrder(Request request) {
		Result result = new Result();
		BackOrder backOrder = backOrders.search(request.getBackOrderId());
		if (backOrder == null) {
			result.setResultCode(Result.BACK_ORDER_NOT_FOUND);
		} else {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setBackOrderFields(backOrder);
		}
		return result;
	}

	/**
	 * This method allows for the purchase for a single appliance type for a single
	 * customer of a variable amount per use case 4. If the quantity of the
	 * appliance requested is not available, then the method processes a purchase of
	 * whatever quantity of appliances is available and generates a back order object
	 * of the unfulfilled quantity. This back order object is stored in the back order
	 * list. Finally, the method returns a Result object with either an error code
	 * or the appropriate fields to verify the purchase of the appliance and/or the
	 * back order generated.
	 * 
	 * @param applianceId - Identifies the model and brand of appliance being
	 *                    purchased.
	 * @param CustomerId  - Identifies the customer purchasing the appliance.
	 * @param quantity    - the amount of appliances being purchased.
	 * @return Result - a code representing the outcome.
	 */
	public Result purchaseModel(Request request) { 
		Result result = new Result();
        int backOrdersNeeded = 0;
        /* This block searches for the customer and appliance. It returns error codes
        if either are not found. TODO: This is guarded in the UI. Should never return bad.
        */
		Customer customer = customers.search(request.getCustomerId());
        if(customer == null) {
            result.setResultCode(Result.CUSTOMER_NOT_FOUND);
            return result;
        }
		Appliance appliance = models.search(request.getApplianceId());
        if(appliance == null) {
            result.setResultCode(Result.APPLIANCE_NOT_FOUND);
            return result;
        }
        /*
        Here, the purchase method in the Appliance class deducts the quantity in the request
        to purchase from the actual number available. If the requested purchase amount exceeds
        the number available, it returns an integer value of the requested amount that could
        not be fulfilled. This amount is used to create the backOrder.
        */
        backOrdersNeeded = appliance.purchase(request.getQuantity());
		Transaction transaction = new Transaction(customer, appliance, Request.instance().getQuantity() - backOrdersNeeded);
        customer.addTransaction(transaction); 
		result.setCustomerFields(customer);
		result.setApplianceFields(appliance);
		result.setQuantity(backOrdersNeeded);  //Note: the quantity being returned is the backOrders needed
		result.setTimeStamp(transaction.getStringStamp());
		result.setResultCode(Result.OPERATION_SUCCESSFUL);

        if (backOrdersNeeded > 0) {
            BackOrder backOrder = new BackOrder(customer, appliance, backOrdersNeeded);
            backOrders.insertBackOrder(backOrder);
			result.setBackOrderId(backOrder.getId());
			result.setQuantity(backOrdersNeeded); //Note: the quantity being returned is the backOrders needed
            result.setResultCode(Result.BACK_ORDER_CREATED);
        }
		return result;
	}

	/**
	 * Charges all repair plans for all customers. 
	 */
	public void chargeRepairPlans() {
		for (Iterator<Customer> customerIterator = customers.iterator(); 
			customerIterator.hasNext();) {
				customerIterator.next().chargeRepairPlans();
		}
	}

	/**
	 * Retrieves a safe iterator for all customers that have a repair plan.
	 * @return Iterator<Result> - iterator of customers.
	 */
	public Iterator<Result> getAllRepairPlanCustomers() {
		return new SafeCustomerIterator(customers.getAllCustomersInRepairPlan().iterator());
	}

	/**
	 * Computes the total revenue from transactions and repair plans.
	 * @return Result - result containing total revenues.
	 */
	public Result getTotalRevenue() {
		double totalRevenueFromTransactions = 0;
		double totalRevenueFromRepairPlans = 0;
		for (Iterator<Customer> customerIterator = customers.iterator(); customerIterator.hasNext();) {
			Customer customer = customerIterator.next();
			totalRevenueFromTransactions += customer.getTransactionTotalCost();
			totalRevenueFromRepairPlans += customer.getRepairPlansTotalCost();
		}
		Result result = new Result();
		result.setTotalRevenueFromTransactions(totalRevenueFromTransactions);
		result.setTotalRevenueFromRepairPlans(totalRevenueFromRepairPlans);
		result.setResultCode(Result.OPERATION_SUCCESSFUL);
		return result;
	}


	/**
	 * Retrieves a safe iterator for customers.
	 * @return Iterator<Result> - iterator of customers.
	 */
	public Iterator<Result> getAllCustomers() {
		return new SafeCustomerIterator(customers.iterator());
	}


	/**
	 * Retrieves a safe iterator for back orders.
	 * @return Iterator<Result> - iterator of back orders.
	 */
	public Iterator<Result> getAllBackOrders() {
		return new SafeBackOrderIterator(backOrders.iterator());
	}

	/**
	 * Saves the data to a file called ApplianceStoreData,
	 * @return Boolean - true if successful, false if not.
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("ApplianceStoreData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(applianceStore);
			Customer.save(output);
			Appliance.save(output);
			BackOrder.save(output);
			Transaction.save(output);
            output.close();
			file.close();
			return true;
		} catch (Exception IOException) {
			IOException.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves the data from the file ApplianceStoreData.
	 * @return ApplianceStore - ApplianceStore object if successful, otherwise null.
	 */
	public static ApplianceStore retrieve() {
		try {
			FileInputStream file = new FileInputStream("ApplianceStoreData");
			ObjectInputStream input = new ObjectInputStream(file);
			applianceStore = (ApplianceStore) input.readObject();
			Customer.retrieve(input);
			Appliance.retrieve(input);
			BackOrder.retrieve(input);
			Transaction.retrieve(input);
            input.close(); // TODO check if neccesary
            file.close(); // TODO check if neccesary
			return applianceStore;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
}
