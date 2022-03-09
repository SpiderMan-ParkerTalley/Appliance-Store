package edu.ics372.project1.appliancestore.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.facade.ApplianceStore;
import edu.ics372.project1.appliancestore.business.facade.Request;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class UserInterface {

	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static ApplianceStore applianceStore;
	private static final int EXIT = 0;
	private static final int ADD_MODEL = 1;
	private static final int ADD_CUSTOMER = 2;
	private static final int ADD_INVENTORY = 3;
	private static final int PURCHASE_MODEL = 4;
	private static final int FULFILL_BACKORDER = 5;
	private static final int ENROLL_REPAIR_PLAN = 6;
	private static final int WITHDRAW_REPAIR_PLAN = 7;
	private static final int CHARGE_REPAIR_PLANS = 8;
	private static final int PRINT_REVENUE = 9;
	private static final int LIST_APPLIANCES = 10;
	private static final int LIST_REPAIR_PLAN_USERS = 11;
	private static final int LIST_CUSTOMERS = 12;
	private static final int LIST_BACKORDERS = 13;
	private static final int SAVE = 14;
	private static final int HELP = 15;

	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			applianceStore = ApplianceStore.instance();
		}

	}

	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);

	}

	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	public String getTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	public void help() {
		System.out.println("Enter a number between 0 and 14 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_MODEL + " to add a single model");
		System.out.println(ADD_CUSTOMER + " to add a single customer");
		System.out.println(ADD_INVENTORY + " to add inventory for a single model");
		System.out.println(PURCHASE_MODEL + " to purchase one or more models for a customer");
		System.out.println(FULFILL_BACKORDER + " to fulfill a single backorder");
		System.out.println(ENROLL_REPAIR_PLAN + " to enroll customer in a repair plan for a single appliance");
		System.out.println(WITHDRAW_REPAIR_PLAN + " to withdraw a customer from a repair plan for a single appliance");
		System.out.println(CHARGE_REPAIR_PLANS + " to charge all repair plans");
		System.out.println(PRINT_REVENUE + " to print revenue from all sales and repair plans");
		System.out.println(LIST_APPLIANCES + " to list all or some types of appliances");
		System.out.println(LIST_REPAIR_PLAN_USERS + " to list all users in repair plans");
		System.out.println(LIST_CUSTOMERS + " to list all customers");
		System.out.println(LIST_BACKORDERS + " to list all backorders");
		System.out.println(SAVE + " to save data to disk");
		System.out.println(HELP + " for help");
	}

	/**
	 * Method to be called for adding a model. The user inputs the prompted valued and 
	 * uses the appropriate ApplicationStore method for adding the model.
	 */
	public void addModel() {
		System.out.println("1 for washer");
		System.out.println("2 for dryer");
		System.out.println("3 for kitchen range");
		System.out.println("4 for refridgerator");
		System.out.println("5 for furnace");
		System.out.println("6 for dishwasher");
		Request.instance().setApplianceType(getNumber("Enter appliance type number"));
		if(Request.instance().getApplianceType() == 1 || Request.instance().getApplianceType() == 2){
			Request.instance().setRepairPlanAmount(getNumber("Enter repair plan price amount"));
		}else if(Request.instance().getApplianceType() == 4){
			Request.instance().setCapacity(getNumber("Enter capacity in liters"));
		}else if(Request.instance().getApplianceType() == 5) {
			Request.instance().setMaxheatingOutput(getNumber("Enter max heating output in BTU"));
		}
		Request.instance().setModelName(getName("Enter model name"));
		Request.instance().setBrandName(getName("Enter brand name"));
		Request.instance().setPrice(getNumber("Enter price"));
		Result result = applianceStore.addModel(Request.instance());
		if(result.getResultCode() != Result.OPERATION_SUCCESSFUL) {
			System.out.println("Could not add appliance model");
		} else {
			System.out.println("Appliance model " + result.getApplianceID() + " has been added");
		}
	}

	/**
	 * Method to be called for adding a customer. The user inputs the prompted valued and 
	 * uses the appropriate ApplicationStore method for adding the customer.
	 */
	public void addCustomer() {
		Request.instance().setCustomerName(getName("Enter customer name"));
		Request.instance().setCustomerAddress(getName("Enter address"));
		Request.instance().setCustomerPhoneNumber(getName("Enter phone"));
		Result result = applianceStore.addCustomer(Request.instance());
		if(result.getResultCode() != Result.OPERATION_SUCCESSFUL) {
			System.out.println("Could not add customer");
		} else {
			System.out.println(result.getCustomerName() + "'s id is " + result.getCustomerId());
		}
	}

	/**
	 * Method to be called for adding inventory to a single model. The user inputs the prompted 
	 * values and uses the appropriate ApplicationStore method for adding the model inventory.
	 */
	public void addInventory() {
		Request.instance().setApplianceID(getToken("Enter appliance id"));
		Result result = applianceStore.searchModel(Request.instance());
		if(result.getResultCode() != Result.OPERATION_SUCCESSFUL) {
			System.out.println("No appliance with id " + Request.instance().getApplianceID());
		} else {
			Request.instance().setQuantity(getNumber("Enter quantity to add"));
			result = applianceStore.addInventory(Request.instance());
		}
		if(result.getResultCode() != Result.OPERATION_SUCCESSFUL) {
			System.out.println("Quantity " + Request.instance().getQuantity() + "could not be added");
		} else {
			System.out.println("Quantity " + Request.instance().getQuantity() + " added");
		}
	}

	/**
	 * Method to be called for purchasing one or more models for a single customer.
	 * The user inputs the promted values and uses the appropriate ApplicationStore 
	 * method purchasing the model.
	 */
	public void purchaseModel() {
		Request.instance().setCustomerId(getToken("Enter customer id"));
		Result result = applianceStore.searchCustomer(Request.instance());
		if (result.getResultCode() != Result.OPERATION_SUCCESSFUL) {
			System.out.println("No customer with id " + Request.instance().getCustomerId());
			return;
		}
		do {
			Request.instance().setApplianceID(getToken("Enter appliance id"));
			Request.instance().setQuantity(getNumber("Enter amount to buy"));
			result = applianceStore.purchaseModel(Request.instance());
			if(result.getResultCode() == Result.OPERATION_SUCCESSFUL) {
				System.out.println("Model " + result.getModelName() + " bought by " + result.getCustomerName()
				+ " on " + result.getTimeStamp());
			} else {
				System.out.println("Model could not be purchased");
			}
		} while (yesOrNo("Purchase more models?"));
	}

	/**
	 * Method to be called for fulfilling the backorders associated with the backorder id.
	 * The user inputs the backorder id and uses the appropriate ApplicationStore 
	 * method for fulfilling the backorder.
	 */
	public void fullFillBackorder() {
		Request.instance().setBackorderId(getToken("Enter backorder id"));
		Result result = applianceStore.searchBackorder(Request.instance()); 
		result = applianceStore.fulfillBackorder(Request.instance());
		if(result.getResultCode() == Result.NOT_A_VALID_QUANTITY){
			System.out.println("Backorder could not be fulfilled due to insufficient inventory");
		} else if(result.getResultCode() == Result.BACK_ORDER_NOT_FOUND) {
			System.out.println("Backorder could not be found");
		} else {
			System.out.println("Backorder fulfilled.");
		}
	}


	//TODO Might have to redo
	/**
	 * Method to be called for enrolling a customer in a repair plan for a single appliance.
	 * The user inputs the customer id and appliance id and uses the appropriate ApplicationStore
	 * method for enrolling the customer in the repair plan.
	 */
	public void enrollRepairPlan() {
		Request.instance().setCustomerId(getToken("Enter customer id"));
		Request.instance().setApplianceID(getToken("Enter appliance id"));
		Result result = applianceStore.enrollRepairPlan(Request.instance());
		if(result.getResultCode() == Result.CUSTOMER_NOT_FOUND) {
			System.out.println("Could not find customer id");
		} else if (result.getResultCode() == Result.APPLIANCE_NOT_FOUND) {
			System.out.println("Could not find appliance id");
		} else if (result.getResultCode() == Result.OPERATION_FAILED) {
			System.out.println("Could not enroll customer in repair plan");
		} else {
			System.out.println("Customer " + Request.instance().getCustomerAddress() + " succesfully " + 
			"enrolled in repair plan for " + Request.instance().getApplianceID());
		}
	}

	/**
	 * Method for withdrawing a customer from a repair plan for a single appliance.
	 * The user inputs the promted values and uses the appropriate ApplicationStore
	 * methods for withdrawing the customer from the repair plan.
	 */
	public void withdrawRepairPlan() {
		Request.instance().setCustomerId(getToken("Enter customer id"));
		Request.instance().setApplianceID(getToken("Enter appliance id"));
		Result result = applianceStore.withdrawRepairPlan(Request.instance());
		if(result.getResultCode() == Result.NOT_ELIGABLE_FOR_REPAIR_PLAN) {
			System.out.println("Appliance not eligable for repair plan");
		} else if(result.getResultCode() == Result.CUSTOMER_NOT_FOUND) {
			System.out.println("Could not find customer id");
		} else if (result.getResultCode() == Result.APPLIANCE_NOT_FOUND) {
			System.out.println("Could not find appliance id");
		} else if (result.getResultCode() == Result.OPERATION_FAILED) {
			System.out.println("Could not enroll customer in repair plan");
		} else {
			System.out.println("Customer " + Request.instance().getCustomerAddress() + " successfully " + 
			"enrolled in repair plan for " + Request.instance().getApplianceID());
			}
		}
	/**
	 * Allows the user to charge all active repair plans to the appropriate customers. Updates all customer accounts
	 * and displays a message when completed.
	 */
	public void chargeAllRepairPlans() {
		applianceStore.chargeRepairPlans();
		System.out.println("All active repair plans have been charged"); //TODO How do we make sure this is true?
	}
	/**
	 * Lists all appliances or a specific type of appliance. It lists each item with its id, brand name, model name, price,
	 * and quantity.
	 */
	public void listAppliances() {
		do {
			System.out.println("1 for washer");
			System.out.println("2 for dryer");
			System.out.println("3 for kitchen range");
			System.out.println("4 for refridgerator");
			System.out.println("5 for furnace");
			System.out.println("6 for dishwasher");
			System.out.println("7 for all");
			Request.instance().setApplianceType(getNumber("Enter appliance type number"));
			Result result = applianceStore.listAppliances(Request.instance());
			if(result.getResultCode() == Result.APPLIANCE_NOT_FOUND) {
				System.out.println("No such models in inventory");
			} else {
				for(Appliance appliance: result.getAppliances()) {
					System.out.println(appliance);
					//Appliance needs toString
				}
			}
		} while (yesOrNo("List another type of appliance models?"));
	}
	/**
	 * Finds all customers with repair plans and prints them out.
	 */
	public void ListAllRepairPlanCustomers() {
		Result result = applianceStore.getAllRepairPlanCustomers();
		for (Customer customer : result.getCustomers()) {
			System.out.println(customer);
		}
	}
	/**
	 * Prints out every customer and their details.
	 */
	public void printAllCustomers() {
		Result result = applianceStore.getAllCustomers();
		for (Customer customer : result.getCustomers())
		System.out.println(customer);
	}
	/**
	 * Prints out all back orders with the appliance brand, model,
	 * customer name, customer id, and quantity
	 * 
	 */
	public void printAllBackOrders() {
		Result result = applianceStore.getAllBackOrders();
		for (BackOrder backOrder : result.getBackOrders())
		System.out.println(backOrder.getAppliance() + " " +
		backOrder.getCustomer() + " " + backOrder.getQuantity());
	}
	/**
	 * Saves the data to a file.
	 */
	private void save() {
		if (ApplianceStore.save()) {
			System.out.println("The system has been successfully saved in the file ApplianceStoreData");
		} else {
			System.out.println("There has been an error in saving.");
		}
	}
	/**
	 * Retrieves the data from a a file
	 */
	private void retrieve()  {
		try {
			if (applianceStore == null) {
				applianceStore = ApplianceStore.retrieve();
				if (applianceStore != null) {
					System.out.println(" The applianceStore has been successfully retrieved from the file LibraryData \n");
				} else {
					System.out.println("File doesnt exist; creating new applianceStore");
					applianceStore = ApplianceStore.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}
/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_MODEL:
				addModel();
				break;
			case ADD_CUSTOMER:
				addCustomer();
				break;
			case ADD_INVENTORY:
				addInventory();
				break;
			case PURCHASE_MODEL:
				purchaseModel();
				break;
			case FULFILL_BACKORDER:
				fullFillBackorder();
				break;
			case ENROLL_REPAIR_PLAN:
				enrollRepairPlan();
				break;
			case WITHDRAW_REPAIR_PLAN:
				withdrawRepairPlan();
				break;
			case CHARGE_REPAIR_PLANS:
				chargeAllRepairPlans();
				break;
			case PRINT_REVENUE:
				printRevenue();
				break;
			case LIST_APPLIANCES:
				listAppliances();
				break;
			case LIST_REPAIR_PLAN_USERS:
				ListAllRepairPlanCustomers();
				break;
			case LIST_CUSTOMERS:
				printAllCustomers();
				break;
			case LIST_BACKORDERS:
				printAllBackOrders();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}
	/**
	 * Prints the revenues from sales and repair plan charge in a convenient
	 * format.
	 */
	private void printRevenue() {
		Result result = applianceStore.getTotalRevenue();
		System.out.println("Sales Revenue: " + result.getTotalRevenueFromTransactions());
		System.out.println("Repair Plan Revenue: " + result.getTotalRevenueFromRepairPlans());
}

	public static void main(String args[]) {
		UserInterface.instance().process();
	}
}
