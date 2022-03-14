package edu.ics372.project1.appliancestore.business.tests;

import java.util.Iterator;

import edu.ics372.project1.appliancestore.business.collections.ModelList;
import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.facade.ApplianceStore;
import edu.ics372.project1.appliancestore.business.facade.Request;
import edu.ics372.project1.appliancestore.business.facade.Result;
import edu.ics372.project1.appliancestore.business.iterators.FilteredApplianceIterator;

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
	
	private static final double repairPlanAmount = 23.50;
	
	private static final double capacity = 100.00;
	
	private static final double maxHeatingOutput = 100_000;

	private static final int quantity = 2;
	// End of appliances information.
	

	// Customer(s) Information - 5 customers total.
	private static final int CUSTOMER_TEST_SIZE = 5;
	// Added when the customers is created.
	private static String[] customerIds = new String[CUSTOMER_TEST_SIZE]; 

	private static final String[] customerNames = { "Christian Zendejas" , 
		" Emmanuel Ojogwu", "James Sawicki", "Sharon Shin", "Parker Talley" };

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
			if (Request.instance().getApplianceType() == 1 || Request.instance().getApplianceType() == 2) {
				// Set the repair plan amount.
				Request.instance().setRepairPlanAmount(repairPlanAmount);
			} 
			// If the appliance is a refrigerator...
			else if (Request.instance().getApplianceType() == 4) {
				// Set the capacity.
				Request.instance().setCapacity(capacity);
			} 
			// If the appliance is a furnace...
			else if (Request.instance().getApplianceType() == 5) {
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
		}
	}


	// Use-case 2 - Add a single customer. 
	public static void addCustomers() {
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
	public static void testPurchaseModels() {
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
			// Customer asserts.
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert result.getCustomerName().equals(customerNames[index]);
			assert result.getCustomerAddress().equals(customerAddresses[index]);
			assert result.getCustomerPhoneNumber().equals(customerPhoneNumbers[index]);
			// Appliance asserts.
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
			assert result.getResultCode() ==  Result.OPERATION_SUCCESSFUL;
		}

		// Test with appliances NOT in-stock. Back orders NOT FULFILLED.
		for (int index = 3; index < 5; index++) {
			// Set back order ID.
			Request.instance().setBackOrderId(backOrderIds[index]);

			// Attempt to fulfill back order.
			Result result = ApplianceStore.instance().fulfillBackOrder(Request.instance());

			// Validate results.
			assert result.getResultCode() ==  Result.NOT_A_VALID_QUANTITY;
		}

		// Test with invalid back order ID. Back order NOT FOUND.
		// Set back order ID.
		Request.instance().setBackOrderId("NOT A BACK ORDER ID");

		// Attempt to fulfill back order.
		Result result = ApplianceStore.instance().fulfillBackOrder(Request.instance());

		// Validate results.
		assert result.getResultCode() ==  Result.BACK_ORDER_NOT_FOUND;
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
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
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


	// Use-case 8 - Charge all repair plans. James
	// TODO: implement testChargeAllRepairPlans. James
	public void testChargeAllRepairPlans() {

	}


	// Use-case 9 - Print total revenue from all sales and repair plans. Emmanuel
	// TODO: change to assert. Emmanuel
	public void testPrintRevenue(){
		Result result = new Result();
		result = ApplianceStore.instance().getTotalRevenue();
		double totalSale = result.getTotalRevenueFromTransactions();
		double totalRepairPlan = result.getTotalRevenueFromRepairPlans();
		System.out.println("The total sale is: " + totalSale + " The total repair plan revenue is: " + totalRepairPlan);
	}


	// Use-case 10 - List all or some types of appliances. Sharon
	public void testListAppliances() {
		final ApplianceStore applianceStore = ApplianceStore.instance();

		// Adding appliances.
		final String applianceModel = "ApplianceModel";
		final String brandName = "Kitchenaid";
		final int[] applianceTypes = { 1, 2, 3, 4, 5, 6 };

		final double price = 5.00;
		final double repairPlanAmount = 10.0;
		final double capacity = 15.0;
		final double maxHeatingOutput = 20.0;

		final Result[] appliances = new Result[6];
		
		for (int count = 0; count < applianceTypes.length; count++) {
			Request.instance().setApplianceType(applianceTypes[count]);
			if (Request.instance().getApplianceType() == 1 || Request.instance().getApplianceType() == 2) {
				Request.instance().setRepairPlanAmount(repairPlanAmount);
			} else if (Request.instance().getApplianceType() == 4) {
				Request.instance().setCapacity(capacity);
			} else if (Request.instance().getApplianceType() == 5) {
				Request.instance().setMaxHeatingOutput(maxHeatingOutput);
			}
			Request.instance().setModelName(applianceModel);
			Request.instance().setBrandName(brandName);
			Request.instance().setPrice(price);
			Result applianceResult = applianceStore.addModel(Request.instance());
			appliances[count] = applianceResult;
		}
		// End of adding appliances.

		// Checking individual appliance types.
		for(int index = 1; index < applianceTypes.length + 1; index++) {
			Request.instance().setApplianceType(index);
			Iterator<Result> iterator = applianceStore.listAppliances(Request.instance());
			while (iterator.hasNext()) {
				Result result = iterator.next();
				assert 0 == (appliances[index - 1].getApplianceId().compareTo(result.getApplianceId()));
				assert 0 == (appliances[index - 1].getModelName().compareTo(result.getModelName()));
				assert 0 == (appliances[index - 1].getBrandName().compareTo(result.getBrandName()));
			}
		}
		// End of checking individual appliance types.

		// Checking all appliance.
		Request.instance().setApplianceType(7);
		Iterator<Result> iterator = applianceStore.listAppliances(Request.instance());
		int count = 0;
		while (iterator.hasNext()) {
			Result result = iterator.next();
			assert 0 == (appliances[count].getApplianceId().compareTo(result.getApplianceId()));
			assert 0 == (appliances[count].getModelName().compareTo(result.getModelName()));
			assert 0 == (appliances[count].getBrandName().compareTo(result.getBrandName()));
			count++;
		}
		// End of checking appliance.

		// Clearing appliance store data
		ApplianceStore.clear();
	}


	// Use-case 11 - List all users in repair plans. Parker
	public void testListAllUsersInRepairPlans() {
		// Creating and adding customers.
		final ApplianceStore applianceStore = ApplianceStore.instance();
		final String[] name = { "Ryan", "Smith" };
		final String[] address = { "75 Rockcrest Street Wellington, FL 33414", 
			"7073 8th St. Warner Robins, GA 31088" };
		final String[] phoneNumber = { "310-788-4084", "434-348-8295" };
		String[] customerIds = { "", "" };

		for(int index = 0; index < name.length; index++) {
			// Set fields.
			Request.instance().setCustomerName(name[index]);
			Request.instance().setCustomerAddress(address[index]);
			Request.instance().setCustomerPhoneNumber(phoneNumber[index]);

			// Create customer.
			Result customerResult = applianceStore.addCustomer(Request.instance());
			customerIds[index] = customerResult.getCustomerId();
		}

		// Adding appliances.
		final String applianceModel = "ApplianceModel";
		final String brandName = "KitchenAid";
		final int[] applianceTypes = { 1, 2, 3, 4, 5, 6 };

		final double price = 5.00;
		final double repairPlanAmount = 10.0;
		final double capacity = 15.0;
		final double maxHeatingOutput = 20.0;

		final Result[] appliances = new Result[6];

		for (int count = 0; count < applianceTypes.length; count++) {
			Request.instance().setApplianceType(applianceTypes[count]);
			// Setting repair plans amount for washer and dryer.
			if (Request.instance().getApplianceType() == 1 || Request.instance().getApplianceType() == 2) {
				Request.instance().setRepairPlanAmount(repairPlanAmount);
			} 
			// Setting capacity for refrigerator
			else if (Request.instance().getApplianceType() == 4) {
				Request.instance().setCapacity(capacity);
			} 
			// Setting heating output for furnace.
			else if (Request.instance().getApplianceType() == 5) {
				Request.instance().setMaxHeatingOutput(maxHeatingOutput);
			}
			// Setting general fields.
			Request.instance().setModelName(applianceModel);
			Request.instance().setBrandName(brandName);
			Request.instance().setPrice(price);
			Result applianceResult = applianceStore.addModel(Request.instance());
			appliances[count] = applianceResult;
		}

		// Purchasing and enrolling customer in repair plan.
		for(int index = 0; index < applianceTypes.length; index++) {
			// Purchasing of repair plan.
			Request.instance().setApplianceID(appliances[index].getApplianceId());
			Request.instance().setCustomerId(customerIds[0]);
			applianceStore.purchaseModel(Request.instance());
			
			// Enrolling in repair plan.
			applianceStore.enrollRepairPlan(Request.instance());
		}
		
		// Testing get all repair plan customers.
		int customerInformationIndex = 0;
		Iterator<Result> iterator = applianceStore.getAllRepairPlanCustomers();
		while (iterator.hasNext()) {
			Result result = iterator.next();
			assert 0 == result.getCustomerName().compareTo(name[customerInformationIndex]);
			assert 0 == result.getCustomerAddress().compareTo(address[customerInformationIndex]);
			assert 0 == result.getCustomerPhoneNumber().compareTo(phoneNumber[customerInformationIndex]);
			assert 0 == result.getCustomerId().compareTo(customerIds[customerInformationIndex]);
			customerInformationIndex++;
		}

		// Clearing appliance store data.
		ApplianceStore.clear();
	}


	// Use-case 12 - List customers. Parker
	public void testListCustomers() {
		// Creating and adding customers.
		final ApplianceStore applianceStore = ApplianceStore.instance();
		final String[] name = { "Ryan", "Smith" };
		final String[] address = { "75 Rockcrest Street Wellington, FL 33414", 
			"7073 8th St. Warner Robins, GA 31088" };
		final String[] phoneNumber = { "310-788-4084", "434-348-8295" };
		String[] customerIds = { "", "" };

		for(int index = 0; index < name.length; index++) {
			// Set fields.
			Request.instance().setCustomerName(name[index]);
			Request.instance().setCustomerAddress(address[index]);
			Request.instance().setCustomerPhoneNumber(phoneNumber[index]);

			// Create customer.
			Result customerResult = applianceStore.addCustomer(Request.instance());
			customerIds[index] = customerResult.getCustomerId();
		}
		
		// Testing list customers.
		Iterator<Result> iterator = applianceStore.getAllCustomers();
		int index = 0;
		while (iterator.hasNext()) {
			Result result = iterator.next();
			assert 0 == result.getCustomerName().compareTo(name[index]);
			assert 0 == result.getCustomerAddress().compareTo(address[index]);
			assert 0 == result.getCustomerPhoneNumber().compareTo(phoneNumber[index]);
			assert 0 == result.getCustomerId().compareTo(customerIds[index]);
			index++;
		}

		// Clearing appliance store data.
		ApplianceStore.clear();
	}


	// Use-case 13 - List all back orders. Sharon
	// TODO: Change to assert, remove extra unused stuff.
	public void testGetAllBackorders() {
		final String name = "Nuel";
		final String address = "007 Krypton Blvd, Asgard, WA 00701";
		final String phoneNumber = "(001)112-2223";

		Request.instance().setCustomerName(name);
		Request.instance().setCustomerAddress(address);
		Request.instance().setCustomerPhoneNumber(phoneNumber);

		final String brandName = "GE";
		final String model = "009";
		final double price = 100.00;
		final int quantity = 1;
		final double repairPlanAmount = 200.00;



		Iterator<Result> resultIterator = store.getAllBackOrders();
		while (resultIterator.hasNext()){
			Result result = resultIterator.next();
				System.out.println(result.getBackOrderId() + " " + result.getCustomerId() + " " +
				result.getApplianceId() + " " +result.getQuantity());
		}
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
		
		System.out.println("Done testing.");
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
