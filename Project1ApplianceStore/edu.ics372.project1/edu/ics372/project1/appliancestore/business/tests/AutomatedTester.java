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
	/*
	 * Test case for adding a single model.
	 */
	private ApplianceStore store = ApplianceStore.instance();
	private int[] appliances = { 1, 2, 3, 4, 5, 6 };
	private String[] brandNames = { "washer", "dryer", "kitchen range", "refrigerator", "furnace", "dishwasher" };
	private String[] modelNames = { "Kitchenaid", "LG", "GE", "Samsung", "Sony", "Zephyr" };

	private double[] prices = { 50.00, 60.00, 100.00, 150.00, 175.00, 200.00 };
	private double repairPlanAmount = 20.50;
	private double capacity = 100.00;
	private double maxHeatingOutput = 225.00;

	// Use-case 1 - Add a single model. Christian
	// TODO: make idependent and add clear() after testing is complete.
	public void testAddAppliance() {
		for (int count = 0; count < appliances.length; count++) {
			Request.instance().setApplianceType(appliances[count]);
			if (Request.instance().getApplianceType() == 1 || Request.instance().getApplianceType() == 2) {
				Request.instance().setRepairPlanAmount(repairPlanAmount);
			} else if (Request.instance().getApplianceType() == 4) {
				Request.instance().setCapacity(capacity);
			} else if (Request.instance().getApplianceType() == 5) {
				Request.instance().setMaxHeatingOutput(maxHeatingOutput);
			}
			Request.instance().setModelName(modelNames[count]);
			Request.instance().setBrandName(brandNames[count]);
			Request.instance().setPrice(prices[count]);
			Result result = ApplianceStore.instance().addModel(Request.instance());
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert result.getBrandName().equals(brandNames[count]);
			assert result.getModelName().equals(modelNames[count]);
			assert result.getPrice() == (prices[count]);
		}
	}


	// Use-case 2 - Add a single customer. Christian
	public static void testAddSingleCustomer() {
		final ApplianceStore applianceStore = ApplianceStore.instance();
		final String name = "Ryan";
		final String address = "75 Rockcrest Street Wellington, FL 33414";
		final String phoneNumber = "310-788-4084";

		Request.instance().setCustomerName(name);
		Request.instance().setCustomerAddress(address);
		Request.instance().setCustomerPhoneNumber(phoneNumber);

		Result result = applianceStore.addCustomer(Request.instance());
		assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
		assert result.getCustomerName().equals(name);
		assert result.getCustomerAddress().equals(address);
		assert result.getCustomerPhoneNumber().equals(phoneNumber);

		ApplianceStore.clear();
	}
	

	// Use-case 3 - Add to inventory for a single model. Christian
	// TODO: Implement testAddToInventoryForModel() Christian
	public void testAddToInventoryForModel() { 

	}


	// Use-case 4 - Purchase one or more models. James
	// TODO: Implement testPurchaseOneOrMoreModels() James
	public void testPurchaseOneOrMoreModels() {

	}

	// Use-case 5 - Fulfill a single back order. Emmanuel
	// TODO: remove extra stuff. Emmanuel 
	public void fulfillBackOrder(){
		// The customer is created and added to the store
		final String name = "Nuel";
		final String address = "007 Krypton Blvd, Asgard, WA 00701";
		final String phoneNumber = "(001)112-2223";

		Request.instance().setCustomerName(name);
		Request.instance().setCustomerAddress(address);
		Request.instance().setCustomerPhoneNumber(phoneNumber);;

		Result resultCustomer = ApplianceStore.instance().addCustomer(Request.instance());

		// The appliance is created and added to the store
		
		final String brandName = "GE";
		final String model = "009";
		final double price = 100.00;
		final int quantity = 1;
		final double repairPlanAmount = 200.00;
    	final double capacity = 60.00;
		final double maxHeatingOutput = 86.0;
		final int amount = 2;
		final int number = 3;
		final int[] applianceTypes = { 1, 2, 3, 4, 5, 6 };

		final Result[] appliances = new Result[6];
		
		for (int count = 0; count < applianceTypes.length; count++) {
			Request.instance().setApplianceType(applianceTypes[count]);
			if (Request.instance().getApplianceType() == 0 || Request.instance().getApplianceType() == 1) {
				Request.instance().setRepairPlanAmount(repairPlanAmount);
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			} else if (Request.instance().getApplianceType() == 3) {
				Request.instance().setCapacity(capacity);
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			} else if (Request.instance().getApplianceType() == 4) {
				Request.instance().setMaxHeatingOutput(maxHeatingOutput);
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			} else if (Request.instance().getApplianceType() == 2 || Request.instance().getApplianceType() == 5) {
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			}
			
			Result applianceResult = ApplianceStore.instance().addModel(Request.instance());
			appliances[count] = applianceResult;
		}
		for(int count = 0; count < applianceTypes.length; count++){
			if(count < 4 && count == 5){
				Request.instance().setCustomerId(resultCustomer.getCustomerId());
				Request.instance().setApplianceID(appliances[count].getApplianceId());
				Request.instance().setQuantity(amount);
				Result resultFulfillBackOrder = ApplianceStore.instance().fulfillBackOrder(Request.instance());
				assert resultFulfillBackOrder.getResultCode() == Result.OPERATION_SUCCESSFUL;
			} else if (count == 4){
				Request.instance().setCustomerId(resultCustomer.getCustomerId());
				Request.instance().setApplianceID(appliances[count].getApplianceId());
				Request.instance().setQuantity(amount);
				Result resultFulfillBackOrder = ApplianceStore.instance().fulfillBackOrder(Request.instance());
				assert resultFulfillBackOrder.getResultCode() == Result.BACK_ORDER_NOT_FOUND;
			}
		
		}
	}


	// Use-case 6 - Enroll a customer in a repair plan for a single appliance. Parker Talley
	public void testEnrollCustomerInRepairPlan() {
		// Creating and adding customer.
		final ApplianceStore applianceStore = ApplianceStore.instance();
		final String name = "Ryan";
		final String address = "75 Rockcrest Street Wellington, FL 33414";
		final String phoneNumber = "310-788-4084";

		Request.instance().setCustomerName(name);
		Request.instance().setCustomerAddress(address);
		Request.instance().setCustomerPhoneNumber(phoneNumber);;

		Result customerResult = applianceStore.addCustomer(Request.instance());
		final String customerId = customerResult.getCustomerId();

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

		// Purchasing and enrolling customer in repair plan.
		for(int index = 0; index < applianceTypes.length; index++) {

			// Purchasing of repair plan.
			Request.instance().setApplianceID(appliances[index].getApplianceId());
			Request.instance().setCustomerId(customerId);
			applianceStore.purchaseModel(Request.instance());
			
		
			// Enrolling in repair plan.
			
			// First appliances of type 1 and 2 are eligible for repair plan.
			if (index <= 1) {
				Result enrollRepairPlanResult = applianceStore.enrollRepairPlan(Request.instance());
				assert enrollRepairPlanResult.getResultCode() == Result.OPERATION_SUCCESSFUL;//getting an assertion error here
			}

			// All other appliance types should not be eligible.
			else if (index >= 2) {
				Result enrollRepairPlanResult = applianceStore.enrollRepairPlan(Request.instance());
				assert enrollRepairPlanResult.getResultCode() == Result.NOT_ELIGIBLE_FOR_REPAIR_PLAN;
			}
		}
		
		// Clearing appliance store data.
		ApplianceStore.clear();
	}

	
	// Use-case 7 - Withdraw customer from a single repair plan. Emmanuel
	// TODO: change to assert, remove extra stuff. Emmanuel
	public void testWithDrawCustomer(){
		final String name = "Nuel";
		final String address = "007 Krypton Blvd, Asgard, WA 00701";
		final String phoneNumber = "(001)112-2223";

		Request.instance().setCustomerName(name);
		Request.instance().setCustomerAddress(address);
		Request.instance().setCustomerPhoneNumber(phoneNumber);;

		Result resultCustomer = ApplianceStore.instance().addCustomer(Request.instance());

		// The appliance is created and added to the store
		final String brandName = "GE";
		final String model = "009";
		final double price = 100.00;
		final int quantity = 1;
		final double repairPlanAmount = 200.00;
    	final double capacity = 60.00;
		final double maxHeatingOutput = 86.0;
		final int amount = 2;
		final int number = 3;
		final int[] applianceTypes = { 1, 2, 3, 4, 5, 6 };

		final Result[] appliances = new Result[6];
		
		for (int count = 0; count < applianceTypes.length; count++) {
			Request.instance().setApplianceType(applianceTypes[count]);
			if (Request.instance().getApplianceType() == 0 || Request.instance().getApplianceType() == 1) {
				Request.instance().setRepairPlanAmount(repairPlanAmount);
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			} else if (Request.instance().getApplianceType() == 3) {
				Request.instance().setCapacity(capacity);
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			} else if (Request.instance().getApplianceType() == 4) {
				Request.instance().setMaxHeatingOutput(maxHeatingOutput);
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			} else if (Request.instance().getApplianceType() == 2 || Request.instance().getApplianceType() == 5) {
				Request.instance().setModelName(model);
				Request.instance().setBrandName(brandName);
				Request.instance().setPrice(price);
			}
			
			Result applianceResult = ApplianceStore.instance().addModel(Request.instance());
			// System.out.println(applianceResult.getResultCode());
			appliances[count] = applianceResult;
		}
		for(int index = 0; index < applianceTypes.length; index++) {
			Request.instance().setApplianceID(appliances[0].getApplianceId());
			Request.instance().setCustomerId(resultCustomer.getCustomerId());
			Result purchaseApplianceResult = ApplianceStore.instance().purchaseModel(Request.instance());
			
			if (index <= 1) {
				Result enrollRepairPlanResult = ApplianceStore.instance().enrollRepairPlan(Request.instance());
				System.out.println(enrollRepairPlanResult.getResultCode());
				Result withDrawCustomerResult = ApplianceStore.instance().withdrawRepairPlan(Request.instance());
				System.out.println(withDrawCustomerResult.getResultCode());
				//assert withDrawCustomerResult.getResultCode() == Result.OPERATION_SUCCESSFUL;
			}
			else if (index >= 2) {
				System.out.println("REPAIR_PLAN_NOT_FOUND");
			}
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

	/*
	Just for testing purposes. TODO: delete before turning in.
	*/
	public void testFilterApplianceIterator() {
		String userInput = "DRY";
		for (Iterator<Appliance> applianceFilteredIterator = new FilteredApplianceIterator(ModelList.getInstance().iterator(), userInput); 
			applianceFilteredIterator.hasNext();) {
				Appliance appliance = applianceFilteredIterator.next();
				
			}
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

	// Use-case 14 - Save data to disk. Sharon
	// TODO: Save data to disk test. Sharon
	public void testSaveDataToDisk() {

	}

/*
TODO: All test method should use 'ApplianceStore.clear()' once they are done testing.
TODO: All test methods should be as indepent as possible (varibles created inside of the method AND clear once done testing). Ask Parker for a reason as to why for explination.
TODO: Add '// Working' after you have tested your method and it meets all the requirements.
*/


	/**
	 * All tests to run here.
	 */
	// TODO: order test cases in a way that test dependences first. Example: testAddSingleCustomer(); come before testEnrollCustomerInRepairPlan();
	public void testAll() {
		System.out.println("Testing...");
		testAddSingleCustomer(); // Working
		testAddAppliance(); // Working
		testEnrollCustomerInRepairPlan(); // Working
		//fulfillBackorder(); //TODO: broken
		//testWithDrawCustomer();
		//testPrintRevenue();
		//testListAppliances();
		testListAppliances(); // Working
		testListAllUsersInRepairPlans(); // Working
		testListCustomers(); // Working
		System.out.println("Done testing.");
	}

	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}
}
