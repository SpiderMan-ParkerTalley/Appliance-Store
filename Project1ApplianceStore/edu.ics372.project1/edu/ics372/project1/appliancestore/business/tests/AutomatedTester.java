package edu.ics372.project1.appliancestore.business.tests;

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
	
	public void testPurchaseModel() { // needs to have a working  and addCustomer to function

	}

}
