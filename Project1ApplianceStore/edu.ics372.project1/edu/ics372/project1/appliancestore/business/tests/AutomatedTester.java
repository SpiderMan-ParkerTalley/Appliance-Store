package edu.ics372.project1.appliancestore.business.tests;

import edu.ics372.project1.appliancestore.business.facade.ApplianceFactory;
import edu.ics372.project1.appliancestore.business.facade.ApplianceStore;
import edu.ics372.project1.appliancestore.business.facade.Request;
import edu.ics372.project1.appliancestore.business.facade.Result;

/**
 * Test suit for use-cases 1-9. 
 * 
 * @author Parker Talley.
 */
public class AutomatedTester {
	// Storing ApplianceStore entity locally.
	private static ApplianceStore applianceStore = ApplianceStore.instance();
	
	// Appliances Information - 20 appliances/models total.
	private static final int APPLIANCE_TEST_SIZE = 20;

	private static String[] applianceIds = new String[APPLIANCE_TEST_SIZE];

	private static final int[] applianceTypes = { 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 
		5, 5, 5, 6, 6, 6 };
	
	private static final String[] brandNames = { "Amana", "Bosch", "Comfort Zone", 
		"GE Appliances", "KitchenAid", "LG", "Moen", "Maytag", "Franke", 
		"Frigidaire", "Sharp", "Insinkerator", "Samsung", "Whirlpool", "Haier", 
		"Sub-Zero", "Viking", "Best Buy Brand", "Bertazzoni", "Thor Kitchen"};
	
	private static final String[] modelNames = { "MD4LJD026G", "WFSZSI842W", "G7C6FFZZF7", 
		"3K4JER8CNS", "VTWTVFV3QZ", "Y2GHK9LWNY", "1Z5KJ64Q0H", "SNXBI25BXQ",
		"B7PBOLRES5", "L1CZEGMFE9", "MYJDIM68SF", "D2O6GNBQ59", "28ADTP0K7Z",
		"BBEP5Q8Y2P", "LDLYCE3LKX", "FPZYVC4Y4I", "PH28IUF2ET", "WTLERQKBU3",
		"L9Y5QJSC6J", "EX8HYM1QL8" };
	
	private static final double[] prices = { 10.00, 20.00, 30.00, 40.00, 50.00, 60.00, 70.00, 
		80.00, 90.00, 100.00, 110.00, 120.00, 130.00, 140.00, 150.00, 160.00, 
		170.00, 180.00, 190.00, 200.00 };
	
	private static final double repairPlanAmount = 50.00;
	
	private static final double capacity = 100.00;
	
	private static final double maxHeatingOutput = 100_000;

	private static final int quantity = 2;
	// End of appliances information.
	

	// Customer(s) Information - 5 customers total.
	private static final int CUSTOMER_TEST_SIZE = 5;
	// Added when the customers is created.
	private static String[] customerIds = new String[CUSTOMER_TEST_SIZE]; 

	private static final String[] customerNames = { "Christian Zendejas" , 
		"Emmanuel Ojogwu", "James Sawicki", "Sharon Shin", "Parker Talley" };

	private static final String[] customerAddresses = {
		"8946 South Fieldstone Ave. Ambler, PA 19002", 
		"839 Tailwater Dr. Ronkonkoma, NY 11779", 
		"8977 NE. Thompson Court East Lansing, MI 48823", 
		"740 Gainsway Ave. Valley Stream, NY 11580", 
		"48 River Ave.Westport, CT 06880" };
	
	private static final String[] customerPhoneNumbers = { "910-554-5621", "631-275-6029", 
		"252-389-8790", "843-889-6979", "843-308-6768" };
	// End of customers information.

	// Purchase Models and Back Orders Information.
	/**
	 * The appliances will be in stock. No back order needed.
	 */
	private static final int inStockQuantity = 2;

	/**
	 * The appliances will be partially in stock. A back order will be needed.
	 */
	private static final int backOrderQuantity = 5;
	
	/**
	 * Quantity needed to be back ordered.
	 */
	private static final int neededBackOrderQuantity = 3;

	/**
	 * Stores the back order IDs.
	 */
	private static String[] backOrderIds = new String[5];
	
	/**
	 * Quantity for adding inventory after creation of back orders.
	 */
	private static final int restockInventory = 5;
	// End of purchase models and back orders information.


	// Use-case 1 - Add a single model.
	public static void addAppliances() {
		for (int index = 0; index < APPLIANCE_TEST_SIZE; index++) {
			// Set the appliance type.
			Request.instance().setApplianceType(applianceTypes[index]);

			// If the appliance is washer or dryer...
			if (Request.instance().getApplianceType() == ApplianceFactory.WASHER || Request.instance().getApplianceType() == ApplianceFactory.DRYER) {
				// Set the repair plan amount.
				Request.instance().setRepairPlanAmount(repairPlanAmount);
			} 
			// If the appliance is a refrigerator...
			else if (Request.instance().getApplianceType() == ApplianceFactory.REFRIGERATOR) {
				// Set the capacity.
				Request.instance().setCapacity(capacity);
			} 
			// If the appliance is a furnace...
			else if (Request.instance().getApplianceType() == ApplianceFactory.FURNACE) {
				// Set the heating output.
				Request.instance().setMaxHeatingOutput(maxHeatingOutput);
			}
			
			// Set remanding information.
			Request.instance().setModelName(modelNames[index]);
			Request.instance().setBrandName(brandNames[index]);
			Request.instance().setPrice(prices[index]);
			
			// Add appliance to the store.
			Result result = ApplianceStore.instance().addModel(Request.instance());

			// Store appliance IDs.
			applianceIds[index] = result.getApplianceId();
			
			// Validate results.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert result.getBrandName().compareTo(brandNames[index]) == 0;
			assert result.getModelName().compareTo(modelNames[index]) == 0;
			assert result.getPrice() == (prices[index]);
			// If the appliance is washer or dryer...
			if (Request.instance().getApplianceType() == ApplianceFactory.WASHER || Request.instance().getApplianceType() == ApplianceFactory.DRYER) {
				// Check if appliance repair plan is set correctly.
				assert result.getEligibleForRepairPlan() == true;
				assert result.getRepairPlanCost() == repairPlanAmount;
				// CHeck if the eligible for back order is set correctly.
				assert result.getEligibleForBackOrder() == true;
			} 
			// If the appliance is a refrigerator...
			else if (Request.instance().getApplianceType() == ApplianceFactory.REFRIGERATOR) {
				// CHeck if the eligible for back order is set correctly.
				assert result.getEligibleForBackOrder() == true;
				// Check if appliance repair plan is set correctly.
				assert result.getEligibleForRepairPlan() == false;
			} 
			// If the appliance is a furnace...
			else if (Request.instance().getApplianceType() == ApplianceFactory.FURNACE) {
				// Check if the eligible for back order is set correctly.
				assert result.getEligibleForBackOrder() == false;
				// Check if appliance repair plan is set correctly.
				assert result.getEligibleForRepairPlan() == false;
			}
			else {
				// CHeck if the eligible for back order is set correctly.
				assert result.getEligibleForBackOrder() == true;
				// Check if appliance repair plan is set correctly.
				assert result.getEligibleForRepairPlan() == false;
			}
		}
	}


	// Use-case 2 - Add a single customer. 
	public void addCustomers() {
		for (int index = 0; index < CUSTOMER_TEST_SIZE; index++) {

			// Set customer information.
			Request.instance().setCustomerName(customerNames[index]);
			Request.instance().setCustomerAddress(customerAddresses[index]);
			Request.instance().setCustomerPhoneNumber(customerPhoneNumbers[index]);

			// Add customer to appliance store.
			Result result = applianceStore.addCustomer(Request.instance());

			// Store customer IDs.
			customerIds[index] = result.getCustomerId();
			
			// Validate results.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert 0 == result.getCustomerName().compareTo(customerNames[index]);
			assert 0 == result.getCustomerAddress().compareTo(customerAddresses[index]);
			assert 0 == result.getCustomerPhoneNumber().compareTo(customerPhoneNumbers[index]);
		}
	}
	

	// Use-case 3 - Add to inventory for a single model. 
	public void testAddToInventoryForModels() { 
		for (int index = 0; index < APPLIANCE_TEST_SIZE; index++) {
			// Set appliance id and quantity.
			Request.instance().setApplianceID(applianceIds[index]);
			Request.instance().setQuantity(quantity);
			
			// Add inventory to appliance store.
			Result result = ApplianceStore.instance().addInventory(Request.instance());

			// Validate results.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert result.getQuantity() == quantity;
			assert result.getBrandName().compareTo(brandNames[index]) == 0;
			assert result.getModelName().compareTo(modelNames[index]) == 0;
			assert result.getPrice() == (prices[index]);
		}
	}


	// Use-case 4 - Purchase one or more models. 
	public void testPurchaseModels() {
		// Stores the current test index for appliance information.
		int applianceIndex = 0;
		
		// Test with appliance in-stock. Back orders NOT NEEDED.
		for (int index = 0; index < CUSTOMER_TEST_SIZE; index++) {
			// Set purchase information.
			Request.instance().setQuantity(inStockQuantity);
			Request.instance().setCustomerId(customerIds[index]);
			Request.instance().setApplianceID(applianceIds[applianceIndex]);

			// Purchase appliances.
			Result result = ApplianceStore.instance().purchaseModel(Request.instance());
			
			// Validate results.
			// Customer checks.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert result.getCustomerName().equals(customerNames[index]);
			assert result.getCustomerAddress().equals(customerAddresses[index]);
			assert result.getCustomerPhoneNumber().equals(customerPhoneNumbers[index]);
			// Appliance checks.
			assert result.getApplianceId().equals(applianceIds[applianceIndex]);
			assert result.getBrandName().equals(brandNames[applianceIndex]);
			assert result.getModelName().equals(modelNames[applianceIndex]);
			assert result.getPrice() == prices[applianceIndex];
			assert result.getQuantity() == 0;

			// Iterator appliance index.
			applianceIndex++;
		}

		// Test with appliance partially in-stock. Back order NEEDED.
		for (int index = 0; index < CUSTOMER_TEST_SIZE; index++) {
			// Set purchase information.
			Request.instance().setQuantity(backOrderQuantity);
			Request.instance().setCustomerId(customerIds[index]);
			Request.instance().setApplianceID(applianceIds[applianceIndex]);

			// Purchase appliances.
			Result result = ApplianceStore.instance().purchaseModel(Request.instance());

			// Store back order ID.
			backOrderIds[index] = result.getBackOrderId();
			
			// Validate results.
			// Customer asserts.
			assert result.getResultCode() == Result.BACK_ORDER_CREATED;
			assert result.getCustomerName().equals(customerNames[index]);
			assert result.getCustomerAddress().equals(customerAddresses[index]);
			assert result.getCustomerPhoneNumber().equals(customerPhoneNumbers[index]);
			// Appliance asserts.
			assert result.getApplianceId().equals(applianceIds[applianceIndex]);
			assert result.getBrandName().equals(brandNames[applianceIndex]);
			assert result.getModelName().equals(modelNames[applianceIndex]);
			assert result.getPrice() == prices[applianceIndex];
			assert result.getQuantity() == neededBackOrderQuantity;

			// Iterator appliance index.
			applianceIndex++;
		}
	}

	/**
	 * Helper method for adding inventory after back orders have been created.
	 */
	public void addInventoryAfterBackOrdersCreation() {
		// Add inventory for 3 of the back orders.
		for (int index = 5; index < 8; index++) {
			// Set appliance id and quantity.
			Request.instance().setApplianceID(applianceIds[index]);
			Request.instance().setQuantity(restockInventory);
			
			// Add inventory to appliance store.
			Result result = ApplianceStore.instance().addInventory(Request.instance());

			// Validate results.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert result.getQuantity() == restockInventory;
			assert result.getBrandName().compareTo(brandNames[index]) == 0;
			assert result.getModelName().compareTo(modelNames[index]) == 0;
			assert result.getPrice() == (prices[index]);
		}
	}


	// Use-case 5 - Fulfill a single back order. 
	public void testFulfillBackOrders() {
		// Restock inventory for test.
		addInventoryAfterBackOrdersCreation();
		
		// Test with appliances in-stock. Back orders FULFILLED.
		for (int index = 0; index < 3; index++) {
			// Set back order ID.
			Request.instance().setBackOrderId(backOrderIds[index]);

			// Fulfill back order.
			Result result = ApplianceStore.instance().fulfillBackOrder(Request.instance());

			// Validate results.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
		}

		// Test with appliances NOT in-stock. Back orders NOT FULFILLED.
		for (int index = 3; index < 5; index++) {
			// Set back order ID.
			Request.instance().setBackOrderId(backOrderIds[index]);

			// Attempt to fulfill back order.
			Result result = ApplianceStore.instance().fulfillBackOrder(Request.instance());

			// Validate results.
			assert result.getResultCode() == Result.NOT_A_VALID_QUANTITY;
		}

		// Test with invalid back order ID. Back order NOT FOUND.
		// Set back order ID.
		Request.instance().setBackOrderId("NOT A BACK ORDER ID");

		// Attempt to fulfill back order.
		Result result = ApplianceStore.instance().fulfillBackOrder(Request.instance());

		// Validate results.
		assert result.getResultCode() == Result.BACK_ORDER_NOT_FOUND;
	}


	// Use-case 6 - Enroll a customer in a repair plan for a single appliance.
	public void testEnrollCustomerInRepairPlan() {
		/* Test with incorrect appliance ID. Customer NOT found.
		Settings appliance ID and customer ID. */
		Request.instance().setApplianceID("NOT AN APPLIANCE ID");
		Request.instance().setCustomerId(customerIds[0]);

		// Enrolling customer in repair plan.
		Result resultApplianceID = applianceStore.enrollRepairPlan(Request.instance());

		// Validate results.
		assert resultApplianceID.getResultCode() == Result.APPLIANCE_NOT_FOUND;

		/* Test with incorrect customer ID. Appliance NOT found.
		Setting appliance ID and customer ID. */
		Request.instance().setApplianceID(applianceIds[0]);
		Request.instance().setCustomerId("NOT A CUSTOMER ID");

		// Enrolling customer in repair plan.
		Result resultCustomerID = applianceStore.enrollRepairPlan(Request.instance());

		// Validate results.
		assert resultCustomerID.getResultCode() == Result.CUSTOMER_NOT_FOUND;

		// Test with proper/correct appliance IDs and customer IDs. Repair plan ENROLLED.
		for (int index = 0; index < CUSTOMER_TEST_SIZE; index++) {
			// Settings appliance ID and customer ID.
			Request.instance().setApplianceID(applianceIds[index]);
			Request.instance().setCustomerId(customerIds[index]);

			// Enrolling customer in repair plan.
			Result result = applianceStore.enrollRepairPlan(Request.instance());

			// Validate results.
			assert result.getResultCode() == Result.REPAIR_PLAN_ENROLLED;
		}


		/* Test with proper/correct customer ID and appliance ID, 
		customer has NOT purchased appliance. */
		int applianceIndex = 10;
		for (int index = 0; index < CUSTOMER_TEST_SIZE; index++) {
			// Settings customer ID and appliance ID.
			Request.instance().setCustomerId(customerIds[index]);
			Request.instance().setApplianceID(applianceIds[applianceIndex++]);

			// Enrolling customer in repair plan.
			Result result = applianceStore.enrollRepairPlan(Request.instance());

			// Validate results.
			assert result.getResultCode() == Result.NOT_ELIGIBLE_FOR_REPAIR_PLAN;
		}
	}

	
	// Use-case 7 - Withdraw customer from a single repair plan. 
	public void testWithdrawCustomerFromRepairPlan() {
		/* Withdraw customer from repair plan with incorrect customer ID.
		Setting customer ID and appliance ID. */
		Request.instance().setCustomerId("NOT A CUSTOMER ID");
		Request.instance().setApplianceID(applianceIds[0]);

		// Withdraw customer from repair plan.
		Result resultCustomerID = ApplianceStore.instance().withdrawRepairPlan(Request.instance());

		// Validation.
		assert resultCustomerID.getResultCode() == Result.CUSTOMER_NOT_FOUND;
		
		/* Withdraw customer from repair plan with incorrect appliance ID.
		Setting customer ID and appliance ID. */
		Request.instance().setCustomerId(customerIds[0]);
		Request.instance().setApplianceID("NOT AN APPLIANCE ID");

		// Withdraw customer from repair plan.
		Result resultApplianceID = ApplianceStore.instance().withdrawRepairPlan(Request.instance());

		// Validation.
		assert resultApplianceID.getResultCode() == Result.APPLIANCE_NOT_FOUND;

		/* Withdraw customer from repair plan with appliance ID not eligible for repair plan.
		Setting customer ID and appliance ID. */
		Request.instance().setCustomerId(customerIds[4]);
		Request.instance().setApplianceID(applianceIds[9]);

		// Withdraw customer from repair plan.
		Result resultApplianceNotEligible = ApplianceStore.instance().withdrawRepairPlan(Request.instance());

		// Validation.
		assert resultApplianceNotEligible.getResultCode() == Result.REPAIR_PLAN_NOT_FOUND;
		
		// Withdraw customer from repair plan. OPERATION SUCCESSFUL.
		for (int index = 0; index < 3; index++) {
			// Setting customer ID and appliance ID.
			Request.instance().setCustomerId(customerIds[index]);
			Request.instance().setApplianceID(applianceIds[index]);

			// Withdraw customer from repair plan.
			Result result = ApplianceStore.instance().withdrawRepairPlan(Request.instance());

			// Validation.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
		}
		
		/* Withdraw customer from repair plan but customer is NOT enrolled in 
		repair plan. */
		for (int index = 0; index < 3; index++) {
			// Setting customer ID and appliance ID.
			Request.instance().setCustomerId(customerIds[index]);
			Request.instance().setApplianceID(applianceIds[index]);

			// Withdraw customer from repair plan.
			Result result = ApplianceStore.instance().withdrawRepairPlan(Request.instance());

			// Validation.
			assert result.getResultCode() == Result.REPAIR_PLAN_NOT_FOUND;
		}
	}


	// Use-case 8 - Charge all repair plans. 
	public void testChargeAllRepairPlans() {
		// Charge all repair plans.
		Result result = ApplianceStore.instance().chargeRepairPlans();

		// Validation.
		assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
		assert result.getAmountCharged() == repairPlanAmount * 2;
	}


	// Use-case 9 - Print total revenue from all sales and repair plans. 
	public void testPrintRevenue() {
		// Compute total revenue.
		Result result = ApplianceStore.instance().getTotalRevenue();

		// Validation.
		assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
		assert result.getTotalRevenueFromTransactions() == 1730.0;
		assert result.getTotalRevenueFromRepairPlans() == repairPlanAmount * 2;
	}

	/**
	 * All tests to run here.
	 */
	public void testAll() {
		System.out.println("Adding appliances...");
		addAppliances();
		System.out.println("Adding customers...\n");
		addCustomers();
		
		System.out.println("Beginning testing...");
		
		System.out.println("Testing adding appliances to inventory...");
		testAddToInventoryForModels();
		System.out.println("Testing adding appliances to inventory COMPLETE!\n");
		
		System.out.println("Testing purchasing appliances...");
		testPurchaseModels();
		System.out.println("Testing purchasing appliances COMPLETE!\n");
		
		System.out.println("Testing fulfilling back orders...");
		testFulfillBackOrders();
		System.out.println("Testing fulfilling back orders COMPLETE!\n");

		System.out.println("Testing enrolling customer in repair plan...");
		testEnrollCustomerInRepairPlan();
		System.out.println("Testing enrolling customer in repair plan COMPLETE!\n");

		System.out.println("Testing withdraw customer from repair plan...");
		testWithdrawCustomerFromRepairPlan();
		System.out.println("Testing withdraw customer from repair plan COMPLETE!\n");

		System.out.println("Testing charge all repair plans...");
		testChargeAllRepairPlans();
		System.out.println("Testing charge all repair plans COMPLETE!\n");

		System.out.println("Testing print revenue...");
		testPrintRevenue();
		System.out.println("Testing print revenue COMPLETE!\n");
		
		System.out.println("Done testing.");
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
