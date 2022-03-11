package edu.ics372.project1.appliancestore.business.tests;

import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.entities.Dryer;
import edu.ics372.project1.appliancestore.business.entities.Furnace;
import edu.ics372.project1.appliancestore.business.entities.KitchenRange;
import edu.ics372.project1.appliancestore.business.entities.Refrigerator;
import edu.ics372.project1.appliancestore.business.entities.Washer;
import edu.ics372.project1.appliancestore.business.facade.ApplianceStore;
import edu.ics372.project1.appliancestore.business.facade.Request;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class AutomatedTester {
	/*
	 * Test case for adding a single model.
	 */
	private ApplianceStore store;
	private int[] appliances = { 1, 2, 3, 4, 5, 6 };
	private String[] brandNames = { "washer", "dryer", "kitchen range", "refrigerator", "furnace", "dishwasher" };
	private String[] modelNames = { "Kitchenaid", "LG", "GE", "Samsung", "Sony", "Zephyr" };

	private double[] prices = { 50.00, 60.00, 100.00, 150.00, 175.00, 200.00 };
	private double repairPlanAmount = 20.50;
	private double capacity = 100.00;
	private double maxHeatingOutput = 225.00;

	public void testAddAppliance() {
		for (int count = 0; count < appliances.length; count++) {
			Request.instance().setApplianceType(appliances[count]);
			if (Request.instance().getApplianceType() == 1 || Request.instance().getApplianceType() == 2) {
				Request.instance().setRepairPlanAmount(repairPlanAmount);
			} else if (Request.instance().getApplianceType() == 4) {
				Request.instance().setCapacity(capacity);
			} else if (Request.instance().getApplianceType() == 5) {
				Request.instance().setMaxheatingOutput(maxHeatingOutput);
			}
			Request.instance().setModelName(modelNames[count]);
			Request.instance().setBrandName(brandNames[count]);
			Request.instance().setPrice(prices[count]);
			Result result = store.addModel(Request.instance());
			assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
			assert result.getBrandName().equals(brandNames[count]);
			assert result.getModelName().equals(modelNames[count]);
			assert result.getPrice() == (prices[count]);
		}
	}

	// Use-case 2 - Add a single customer.
	public static void testAddSingleCustomer() {
		final ApplianceStore applianceStore = ApplianceStore.instance();
		final String name = "Ryan";
		final String address = "75 Rockcrest Street Wellington, FL 33414";
		final String phoneNumber = "310-788-4084";

		Request.instance().setCustomerName(name);
		Request.instance().setCustomerAddress(address);
		Request.instance().setCustomerPhoneNumber(phoneNumber);;

		Result result = applianceStore.addCustomer(Request.instance());
		assert result.getResultCode() == Result.OPERATION_SUCCESSFUL;
		assert result.getCustomerName().equals(name);
		assert result.getCustomerAddress().equals(address);
		assert result.getCustomerPhoneNumber().equals(phoneNumber);
	}
	
	public void testPurchaseModel() { // needs to have a working addModel and addCustomer to function

	}
	// Use Case 5 Fulfill a single backOrder
	public void fulfillBackorder(){
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
		final int amount = 2;
		final int number = 3;

		
		if(number == 0 || number == 1){
			Request.instance().setRepairPlanAmount(repairPlanAmount);
			Request.instance().setBrandName(brandName);
			Request.instance().setModelName(model);
			Request.instance().setPrice(price);
			Request.instance().setQuantity(quantity);
		}
		else if(number == 3){
			Request.instance().setCapacity(capacity);
			Request.instance().setBrandName(brandName);
			Request.instance().setModelName(model);
			Request.instance().setPrice(price);
			Request.instance().setQuantity(quantity);
		}
		else if(number == 2 || number == 5){
			Request.instance().setBrandName(brandName);
			Request.instance().setModelName(model);
			Request.instance().setPrice(price);
			Request.instance().setQuantity(quantity);
		}
		Result resultAppliance = ApplianceStore.instance().addModel(Request.instance());
		Request.instance().setCustomerId(resultCustomer.getCustomerId());
		Request.instance().setApplianceID(resultAppliance.getApplianceId());
		Request.instance().setQuantity(amount);
		Result resultFulfillBackOrder = ApplianceStore.instance().fulfillBackorder(Request.instance());
		System.out.println(resultFulfillBackOrder.getResultCode());
		assert resultFulfillBackOrder.getResultCode() == Result.OPERATION_SUCCESSFUL;
		
	}

	// Use-case 6 - Enroll a custmer in a repair plan for a single appliance.
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
		final String brandName = "Kitchenaid";
		final int[] applianceTypes = { 1, 2, 3, 4, 5, 6 };

		final double price = 5.00;
		final double repairPlanAmount = 10.0;
		final double capacity = 15.0;
		final double maxHeatingOutput = 20.0;

		String[] applianceIDs = new String[6];

		for (int count = 0; count < applianceTypes.length; count++) {
			Request.instance().setApplianceType(applianceTypes[count]);
			if (Request.instance().getApplianceType() == 1 || Request.instance().getApplianceType() == 2) {
				Request.instance().setRepairPlanAmount(repairPlanAmount);
			} else if (Request.instance().getApplianceType() == 4) {
				Request.instance().setCapacity(capacity);
			} else if (Request.instance().getApplianceType() == 5) {
				Request.instance().setMaxheatingOutput(maxHeatingOutput);
			}
			Request.instance().setModelName(applianceModel);
			Request.instance().setBrandName(brandName);
			Request.instance().setPrice(price);
			Result applianceResult = applianceStore.addModel(Request.instance());
			applianceIDs[count] = applianceResult.getApplianceId();
		}

		// TODO: add purchasing of appliance.

		// Enrolling customer in repair plan.
		for(int index = 0; index < applianceIDs.length; index++) {
			Request.instance().setApplianceID(applianceIDs[index]);
			Request.instance().setCustomerId(customerId);
			Result enrollRepairPlanResult = applianceStore.enrollRepairPlan(Request.instance());
			if (index <= 1) {
				System.out.println(enrollRepairPlanResult.getResultCode());
				assert enrollRepairPlanResult.getResultCode() == Result.OPERATION_SUCCESSFUL;//getting an assertion error here
			}
			else if (index >= 2) {
				assert enrollRepairPlanResult.getResultCode() == Result.NOT_ELIGIBLE_FOR_REPAIR_PLAN;
			}
		}

	}
	//Use Case 7 Withdraw Customer from a single repair plan
	// The customer is created and added to the store
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
		Request.instance().setBrandName(brandName);
		Request.instance().setModelName(model);
		Request.instance().setPrice(price);
		Request.instance().setQuantity(quantity);
		Result resultAppliance = ApplianceStore.instance().addModel(Request.instance());
		// The customer is first added to a repair plan
		Request.instance().setApplianceID(resultAppliance.getApplianceId());
		Request.instance().setCustomerId(resultCustomer.getCustomerId());
		Result resultEnrollRepairPlan = ApplianceStore.instance().enrollRepairPlan(Request.instance());
		// The customer is removed from the repair plan;
		Result resultWithDrawRepairPlan = ApplianceStore.instance().withdrawRepairPlan(Request.instance());
		assert resultWithDrawRepairPlan.getResultCode() == Result.OPERATION_SUCCESSFUL;
	}
	// Use Case 9 Print total Revenue
	public void testPrintRevenue(){
		Result result = new Result();
		result = ApplianceStore.instance().getTotalRevenue();
		double totalSale = result.getTotalRevenueFromTransactions();
		double totalRepairPlan = result.getTotalRevenueFromRepairPlans();
		System.out.println("The total sale is: " + totalSale + "The total repair plan revenue is: " + totalRepairPlan);
	}


	/**
	 * All tests to run here.
	 */
	public void testAll() {
		System.out.println("Testing...");
		testAddSingleCustomer(); 
		testEnrollCustomerInRepairPlan(); // TODO: Will need to be tested after add customer and add appliance.
//		testFulfillBackOrder(); TODO: broken
		testWithDrawCustomer();
		testPrintRevenue();
		System.out.println("Done testing.");
	}

	public static void main(String[] args) {
		Washer washer = new Washer("washer100", "LG", 150.00, 1, 25.00);
		System.out.println(washer);
		
		Dryer dryer = new Dryer("dryer50", "GE", 124.00, 2, 30.00);
		System.out.println(dryer);
		
		Furnace furnace = new Furnace("furnace1", "LG", 200.00, 1, 30.00);
		System.out.println(furnace);
		
		KitchenRange kit = new KitchenRange("kit1", "GE", 300.00, 2);
		System.out.println(kit);
		
		Refrigerator fridge = new Refrigerator("fridge", "LE", 200.00, 1, 25.25);
		System.out.println(fridge);
		new AutomatedTester().testAll();
	}
}
