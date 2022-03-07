package edu.ics372.project1.appliancestore.business.facade;

import java.io.Serializable;
import java.util.Iterator;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.RepairPlan;
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

//	private static customers = CustomerList.instance();
//	private static models = ModelList.instance();
//	private static backOrders = BackOrderList.instance();

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
	public Result purchaseAppliance(String applianceId, String customerId, int quantity) {
		Result result = new Result();
        Customer customer = CustomerList.search(customerId);
        Appliance appliance = ModelList.search(applianceId);
        int backOrdersNeeded = 0;

        // check for valid entries
        if (customer == null) {
            result.setResultCode(2);
            return result;
        }
        else if (appliance == null) {
            result.setResultCode(1);
            return result;
        }

        /*
        This block makes the purchase and checks to see if there is a need to create a backOrder. If there is,
        a backOrder is created for the amount of appliances that are not in stock via the 
        return of purchase, which returns the number of appliances in the order that are not
        fulfilled. This amount is then sent to a backOrder object and added to the backOrder list.
        .
        */
        backOrdersNeeded = appliance.purchase(quantity);
        if (backOrdersNeeded > 0) {
            result = BackOrderList.createBackOrder(customerId, applianceId, backOrdersNeeded);
            result.setResultCode(6);
            return result;
        }
        else {
            result.setResultCode(4);
            return result;
        }
	}

    /**
     * Charges all repair plans for all customers. The method acquires an iterator
     * from the customerList and then examines each customer. It grabs a repairPlan iterator
     * from each customer and charges each repair plan it finds. With each repair plan charge,
     * it generates a transaction object and stores it in the customer's transactionList .
     */
    public void chargeRepairPlans() {
        for (Iterator<Customer> customerIterator = CustomerList.getCustomerIterator(); 
            customerIterator.hasNext() ) {
                Customer customer = customerIterator.next();
            for (Iterator<RepairPlan> repairPlanIterator = customer.getRepairPlanIterator(); 
            repairPlanIterator.hasNext()) {
                RepairPlan currentPlan = repairPlanIterator.next();
                customer.transactionList.addTransaction(); // TODO. FLESH OUT addTransaction for repair plans and finish
            }
        }

    }
}
