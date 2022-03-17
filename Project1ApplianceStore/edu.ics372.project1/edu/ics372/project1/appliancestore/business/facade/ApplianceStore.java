package edu.ics372.project1.appliancestore.business.facade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.ApplianceWithRepairPlan;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.RepairPlan;
import edu.ics372.project1.appliancestore.business.entities.SaleTransaction;
import edu.ics372.project1.appliancestore.business.entities.Transaction;
import edu.ics372.project1.appliancestore.business.iterators.FilteredApplianceIterator;
import edu.ics372.project1.appliancestore.business.iterators.SafeApplianceIterator;
import edu.ics372.project1.appliancestore.business.iterators.SafeBackOrderIterator;
import edu.ics372.project1.appliancestore.business.iterators.SafeCustomerIterator;
import edu.ics372.project1.appliancestore.business.collections.CustomerList;
import edu.ics372.project1.appliancestore.business.collections.BackOrderList;
import edu.ics372.project1.appliancestore.business.collections.ModelList;

/**
 * This is a facade class for appliance store. It handles all the requests from 
 * users.
 * 
 * @author Jim Sawicki, Sharon Shin, Parker Talley, Emmanuel Ojogwu, and 
 * Cristian Zendejas.
 */
public class ApplianceStore implements Serializable {
	/**
     * For serialization/de-serialization of the data.
     */
	private static final long serialVersionUID = 1L;

	/**
	 * Stores the singleton of appliance store.
	 */
	private static ApplianceStore applianceStore;

	/**
	 * Stores all the customers.
	 */
	private CustomerList customers = CustomerList.getInstance();

	/**
	 * Stores all the models of appliances.
	 */
	private ModelList models = ModelList.getInstance();

	/**
	 * Stores all the back orders.
	 */
	private BackOrderList backOrders = BackOrderList.getInstance();

	/**
	 * The constructor is private in order to implement the singleton design
	 * pattern.
	 */
	private ApplianceStore() {
	}

	/**
	 * If no singleton has been created, creates a singleton. If a singleton 
	 * already exists, returns the singleton.
	 * 
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
	 * Adds a new model to the appliance store.
	 * 
	 * @param request Request contains appliance code appliance code, brand name,
	 * model name, price, quantity and possible more given the type of the 
	 * appliance.
	 * @param type int pertaining to appliance code.
	 * @param brandName String brand name of the appliance.
	 * @param modelName String model name of the appliance.
	 * @param price double price of the appliance.
	 * @param quantity int number of the appliance in-stock.
	 * @return Result result containing result code and if successful the new 
	 * appliance details.
	 */
	public Result addModel(Request request) {
		// Create result object that will be returned.
		Result result = new Result();

		// Generate new appliance using appliance factory.
		Appliance appliance = ApplianceFactory.createAppliance(request.getApplianceType(), request);
		if (models.insertModel(appliance)) {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setApplianceFields(appliance);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Adds a new customer to the appliance store.
	 * 
	 * @param request Request contains name, address, and phone number.
	 * @param customer String name of the customer.
	 * @param customer String address of the customer.
	 * @param customer String phone number of the customer.
	 * @return Result result containing result code and 
	 */
	public Result addCustomer(Request request) {
		// Create result object that will be returned.
		Result result = new Result();

		// Create the new customer object.
		Customer customer = new Customer(request.getCustomerName(), 
			request.getCustomerAddress(), request.getCustomerPhoneNumber());

		// Attempt to insert the customer into customers collection.
		if (customers.insertCustomer(customer)) {
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			result.setCustomerFields(customer);
		}
		// If insertion failed...
		else {
			result.setResultCode(Result.OPERATION_FAILED);
		}
		// Return the result object.
		return result;
	}

	/**
	 * Adds inventory for a single model.
	 * 
	 * @param request Request contains appliance ID.
	 * @param String applianceId the appliance's ID.
	 * @return Result result containing result code and appliance 
	 */
	public Result addInventory(Request request) {
		// Create result object that will be returned.
		Result result = new Result();

		// Check if appliance exist in the system.
		Appliance appliance = models.search(request.getApplianceId());
		// If the appliance does not exist in the system...
		if (appliance == null) {
			// Set return object field.
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
		}
		// If the appliance does exist in the system...
		else {
			// Set new quantity appliance model quantity.
			appliance.setQuantity(request.getQuantity() + appliance.getQuantity());
			// Set return object fields.
			result.setApplianceFields(appliance);
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
		}
		// Return the result object.
		return result;
	}

	/**
	 * Fulfill a back order, given correct circumstances.
	 * 
	 * @param request Request contains back order ID.
	 * @param String backOrderId the back order ID.
	 * @return Result result containing result code and if successful transaction
	 * information, back order information, customer information and appliance
	 * information.
	 */
	public Result fulfillBackOrder(Request request) {
		// Create result object that will be returned.
		Result result = new Result();

		// Check if the back order exist in the system.
		BackOrder backOrder = backOrders.search(request.getBackOrderId());
		if (backOrder == null) {
			result.setResultCode(Result.BACK_ORDER_NOT_FOUND);
		} 
		
		// If the back order exist in the system....
		else {
			// Check if there is enough inventory in the appliance store to fulfill back order.
			if (backOrder.getQuantity() > models.search(backOrder.getAppliance().getId()).getQuantity()) {
				result.setResultCode(Result.NOT_A_VALID_QUANTITY);
			} 
			// If there is enough inventory in the appliance store to fulfill back order...
			else {
				SaleTransaction transaction = new SaleTransaction(backOrder.getCustomer(), 
					backOrder.getAppliance(), backOrder.getQuantity());
				
				// Check if transaction can be added to customer.
				if (backOrder.getCustomer().addSaleTransaction(transaction)) {
					// Remove the back order from the back order list.
					backOrders.removeBackOrder(backOrder);
					// Compute new quantity of appliance model.
					int newQuantity = models.search(backOrder.getAppliance().getId()).getQuantity() 
						- backOrder.getQuantity();
					// Locate actual appliance object.
					Appliance appliance = models.search(backOrder.getAppliance().getId());
					// Set new appliance quantity.
					appliance.setQuantity(newQuantity);
					
					// Set return object fields.
					result.setSaleTransactionFields(transaction);
					result.setBackOrderFields(backOrder);
					result.setCustomerFields(backOrder.getCustomer());
					result.setApplianceFields(appliance);
					result.setResultCode(Result.OPERATION_SUCCESSFUL);
				}
			}
		}
		// Return the result object.
		return result;
	}

	/**
	 * Enrolls a customer into a repair plan. 
	 * 
	 * @param request Request contains customer ID and appliance ID.
	 * @param String customerId the customer's ID.
	 * @param String applianceId the appliance's ID.
	 * @return Result result containing result code.
	 */
	public Result enrollRepairPlan(Request request) {
		// Create result object that will be returned.
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
		
		// Validating that customer has purchased this appliance. If true, enroll
		Iterator<SaleTransaction> saleTransactionIterator = customer.getSalesTransactionIterator();
		while(saleTransactionIterator.hasNext()) {
			SaleTransaction transaction = saleTransactionIterator.next();
			// If customer has purchased the appliance...
			if (transaction.getAppliance().getId().equals(appliance.getId())) {
				customer.addRepairPlan((ApplianceWithRepairPlan) appliance);
				result.setCustomerFields(customer);
				result.setApplianceFields(appliance);
				result.setResultCode(Result.REPAIR_PLAN_ENROLLED);
				return result;
			}
		}
		// If customer has NOT purchased the appliance, set the proper fields.
		result.setCustomerFields(customer);
		result.setApplianceFields(appliance);
		result.setResultCode(Result.CUSTOMER_HAS_NOT_PURCHASED_APPLIANCE); 
		// Return the result object.
		return result;
	}

	/**
	 * Withdraw a customer from a repair plan for a single model. 
	 * 
	 * @param request Request contains customer ID and appliance ID.
	 * @param String customerId the customer's ID.
	 * @param String applianceId the appliance's ID.
	 * @return Result result containing result code AND repair plan if operation
	 * was successful.
	 */
	public Result withdrawRepairPlan(Request request) {
		// Create result object that will be returned.
		Result result = new Result();

		// Check if customer exist in the system.
		Customer customer = customers.search(request.getCustomerId());
		if (customer == null) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		}	

		// Check if appliance exist in the system.
		Appliance appliance = models.search(request.getApplianceId());
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
			return result;
		}
		
		// Check if repair plan associated to customer exist in the system.
		RepairPlan repairPlan = 
			customers.search(request.getCustomerId()).searchRepairPlan(request.getApplianceId());
		if (repairPlan == null) {
			result.setResultCode(Result.REPAIR_PLAN_NOT_FOUND);
			return result;
		}

		// Set return object fields.
		result.setApplianceFields(appliance);
		result.setCustomerFields(customer);
		result.setRepairPlanFields(repairPlan);

		// Remove the repair plan from customer. 
		if (customer.removeRepairPlan(repairPlan)) {
			// If the operation is successful...
			result.setResultCode(Result.OPERATION_SUCCESSFUL);
			return result;
		}
		else {
			// Return if the operation was unsuccessful...
			result.setResultCode(Result.OPERATION_FAILED);
			return result;
		}
		
	}


	/**
	 * Retrieve an iterator for the appliances requested. A category is chosen 
	 * and passed as a parameter.
	 * 
	 * @param request Request contains int pertaining to appliance type.
	 * @param applianceType int appliance category code (1-7).
	 * @return Iterator<Result> iterator of appliances.
	 */
	public Iterator<Result> listAppliances(Request request) {
		// Returns iterator for all appliance(s)
		if (request.getApplianceType() == 7) {
			return new SafeApplianceIterator(models.iterator());
		} 
		// Returns an iterator for a select category of appliances.
		String applianceCode = ApplianceFactory.findApplianceType(request.getApplianceType());
		return new SafeApplianceIterator(new FilteredApplianceIterator(models.iterator(), applianceCode));
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
	 * 
	 * @param request Request contains customer id.
	 * @param customerId String customer id.
	 * @return Result a code representing the outcome AND customer information.
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
	 * @param backOrderId String the back order id.
	 * @return Result a code representing the outcome AND back order information.
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
		Appliance appliance;
		Customer customer;
        /* This block searches for the customer and appliance. It returns error
		codes if the customer ID or appliance ID cannot be located.
        */
		customer = customers.search(Request.instance().getCustomerId());
		if (Objects.isNull(customer)) {
			result.setResultCode(Result.CUSTOMER_NOT_FOUND);
			return result;
		} 
		appliance = models.search(request.getApplianceId());
		if (Objects.isNull(appliance)) {
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
		SaleTransaction transaction = new SaleTransaction(customer, appliance, Request.instance().getQuantity() - backOrdersNeeded);
        customer.addSaleTransaction(transaction); 
		result.setCustomerFields(customer);
		result.setApplianceFields(appliance);
		result.setQuantity(backOrdersNeeded);  //Note: the quantity being returned is the backOrders needed
		result.setTimeStamp(transaction.getStringTimeStamp());
		result.setResultCode(Result.OPERATION_SUCCESSFUL);

        if (backOrdersNeeded > 0) {
			if (appliance.eligibleForBackOrder()) {
            	BackOrder backOrder = new BackOrder(customer, appliance, backOrdersNeeded);
           		backOrders.insertBackOrder(backOrder);
				result.setBackOrderId(backOrder.getId());
				result.setQuantity(backOrdersNeeded); //Note: the quantity being returned is the backOrders needed
            	result.setResultCode(Result.BACK_ORDER_CREATED);
			} else {
				result.setResultCode(Result.PARTIAL_FULFILLMENT);
			}
        }
		return result;
	}

	/**
	 * Charges all repair plans for all customers. 
	 */
	public Result chargeRepairPlans() {
		Result result = new Result();
		Double amountCharged = 0.0;
		for (Iterator<Customer> customerIterator = customers.iterator(); 
			customerIterator.hasNext();) {
				Customer customer = customerIterator.next();
				double customerAmountCharged = customer.chargeRepairPlans();
				if (customerAmountCharged < 0) {
					result.setResultCode(Result.OPERATION_FAILED);
					return result;
				}
				amountCharged += customerAmountCharged;
		}
		result.setResultCode(Result.OPERATION_SUCCESSFUL);
		result.setAmountCharged(amountCharged);
		return result;
	}

	/**
	 * Retrieves a safe iterator for all customers that have a repair plan.
	 * @return Iterator<Result> iterator of customers.
	 */
	public Iterator<Result> getAllRepairPlanCustomers() {
		return new SafeCustomerIterator(customers.getAllCustomersInRepairPlan().iterator());
	}

	/**
	 * Computes the total revenue from transactions and repair plans.
	 * @return Result result containing total revenues.
	 */
	public Result getTotalRevenue() {
		double totalRevenueFromTransactions = 0;
		double totalRevenueFromRepairPlans = 0;
		for (Iterator<Customer> customerIterator = customers.iterator(); customerIterator.hasNext();) {
			Customer customer = customerIterator.next();
			totalRevenueFromTransactions += customer.getSalesTotalCost();
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
            input.close(); 
            file.close(); 
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
