package edu.ics372.project1.appliancestore.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
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

	/**
	 * Private constructor to support the singleton pattern.
	 * Asks if they user would like to attempt to load a saved data
	 * state, then either retrieves that state or calls the instance()
	 * method to return an ApplianceStore singleton.
	 */
	private UserInterface() {
		if (yesOrNo("Look for saved data and load it if found?")) {
			retrieve();
		} else {
			applianceStore = ApplianceStore.instance();
		}

	}

	/**
	 * Applies the singleton pattern to UserInteface.
	 * If an instance of userInterface already exists,
	 * it returns that instance. Otherwise, it calls the
	 * constructor and returns a new instance.
	 * @return A userInterface object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * String tokenizer to help with user input.
	 * @param prompt
	 * @return
	 */
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

	/**
	 * Grabs input from the user using a prompt and returns it for the name field.
	 * @param prompt The text prompt the system will display to the user.
	 * @return The name
	 */
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

	/**
	 * Boolean yes/no for continuing or exiting menu loops.
	 * @param prompt The prompt the user sees.
	 * @return Boolean true for yes, false for no
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Prompts for an integer value of a number.
	 * Takes a string as argument, converters to Integer, then returns as int.
	 * @param prompt The prompt displayed to the user
	 * @return The primative int value
	 */
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

/**
 * Directs the flow of the system via integer input from the user. 
 * Offers to save data upon an exit request.
 * @return an int
 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					if (value == EXIT) {
						if (yesOrNo("Would you like to save before exiting? ")) {
							save();
						}
					}
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Help menu to remind user of input values.
	 */
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
	 * Method to be called for adding a model. Takes user input and 
	 * uses the appropriate ApplicationStore method for adding the model.
	 */
	public void addModel() {
		boolean goodInput = false;
		final int  MINIMUM_MENU_INPUT = 1;
		final int MAXIMUM_MENU_INPUT = 6;
		modelSubMenu();
		while ( !goodInput) {
			Request.instance().setApplianceType(getNumber("Enter appliance type number"));  // TODO: GUARD AGAINST BAD INPUT
			if (Request.instance().getApplianceType() > MAXIMUM_MENU_INPUT || Request.instance().getApplianceType() < MINIMUM_MENU_INPUT) {
				System.out.println("This is not a valid menu selection. Please select from the following options.");
				modelSubMenu();
			}
			else {
				goodInput = true;
			}
		}

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
			System.out.println("Appliance model " + result.getApplianceId() + " has been added");
		}
	}
	/**
	 * Helper function for addModel.
	 */
	private void modelSubMenu() {
		System.out.println("1 for washer");
		System.out.println("2 for dryer");
		System.out.println("3 for kitchen range");
		System.out.println("4 for refrigerator");
		System.out.println("5 for furnace");
		System.out.println("6 for dishwasher");
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
			System.out.println("No appliance with id " + Request.instance().getApplianceId());
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
		Request.instance().reset(); // TODO Watch this, I think it's a good idea to reset in the UI with each new request. Thoughts?
		Result result = new Result();
		do {
		// customer and appliance search guards
		customerCheck(); 
		applianceCheck();
			Request.instance().setQuantity(getNumber("Enter amount to buy"));
			result = applianceStore.purchaseModel(Request.instance());
			switch(result.getResultCode()) {
			case Result.OPERATION_SUCCESSFUL:
				purchaseModelSuccessfulOutput(result);
				break;
			case Result.BACKORDER_CREATED:
				purchaseModelSuccessfulOutput(result);
				System.out.printf("Could not fulfill entire order." +
				" Back Order created with Back Order ID %s. Quantity back ordered: %d%n",
				 result.getBackorderId(), result.getQuantity());
				break;
			default:
				System.out.println("Could not process order.");
			}
		} while (yesOrNo("Purchase more models?")); //TODO Does the use case specifically ask for this?
	}

	/**
	 * Helper method for the output of PurchaseModel.
	 */
	private void purchaseModelSuccessfulOutput(Result result) {
		System.out.println((Request.instance().getQuantity() - result.getQuantity()) + 
					" of Appliance Brand " + result.getBrandName() + ", Model " + 
					result.getModelName() +	" bought by " + result.getCustomerName() + 
					" on " + result.getTimeStamp());
	}

	/**
	 * Helper method to check the validity of the applianceId.
	 */
	private void applianceCheck() {
		Result result = new Result();
		boolean applianceInputGood = false;
		while(!applianceInputGood) {
			Request.instance().setApplianceID(getToken("Enter appliance id"));
			result = applianceStore.searchModel(Request.instance());
			if(result.getResultCode() == Result.APPLIANCE_NOT_FOUND) {
					System.out.println("Error: Appliance with id " + Request.instance().getApplianceId() +
					" not found.");
				}
			else if (result.getResultCode() == Result.OPERATION_SUCCESSFUL) {
				applianceInputGood = true;
			}
		}
	}
	/**
	 * Helper method to check the validity of the applianceId.
	 */
	private void customerCheck() {
		Result result = new Result();
		boolean customerInputGood = false;
		while(!customerInputGood) {
			Request.instance().setCustomerId(getToken("Enter customer id"));
			result = applianceStore.searchCustomer(Request.instance());
			if(result.getResultCode() == Result.CUSTOMER_NOT_FOUND) {
				System.out.println("Error: Customer with id " + Request.instance().getCustomerId() +
				" not found.");
			}
			else if(result.getResultCode() == Result.OPERATION_SUCCESSFUL) {
				customerInputGood = true;
			}
		}
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
		} else if (result.getResultCode() == Result.REPAIR_PLAN_ENROLLED) {
			System.out.println("Customer " + result.getCustomerId() + " succesfully " + 
			"enrolled in repair plan for " + result.getApplianceId());
		} else if (result.getResultCode() == Result.CUSTOMER_HAS_NOT_PURCHASED_APPLIANCE) {
			System.out.println("Cannot enroll customer in repair plan." + 
								"This customer has not purchased this appliance.");
		} else if (result.getResultCode() == Result.NOT_ELIGIBLE_FOR_REPAIR_PLAN) {
			System.out.println("This appliance is not eligible for a repair plan.");
		} else {
			System.out.println("There was an error enrolling the plan.");
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
		if(result.getResultCode() == Result.NOT_ELIGIBLE_FOR_REPAIR_PLAN) {
			System.out.println("Appliance not eligable for repair plan");
		} else if(result.getResultCode() == Result.CUSTOMER_NOT_FOUND) {
			System.out.println("Could not find customer id");
		} else if (result.getResultCode() == Result.APPLIANCE_NOT_FOUND) {
			System.out.println("Could not find appliance id");
		} else if (result.getResultCode() == Result.OPERATION_FAILED) {
			System.out.println("Could not enroll customer in repair plan");
		} else {
			System.out.println("Customer " + Request.instance().getCustomerAddress() + " successfully " + 
			"enrolled in repair plan for " + Request.instance().getApplianceId());
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
	public void listCustomers() {
		Iterator<Result> resultIterator = applianceStore.getAllCustomers();
		System.out.println("Name | Address | Phone| Repair Plan Status");
		System.out.println("----------------------------------------------------" +
							"------------------------------------");
		while (resultIterator.hasNext()){
		Result result = resultIterator.next();
		System.out.println(result.getCustomerName() + " | "
						 + result.getCustomerAddress() + " | "
						 + result.getCustomerPhoneNumber() + " | "
						 + result.getCustomerHasRepairPlan());
	}
}
	/**
	 * Prints out all back orders with the appliance brand, model,
	 * customer name, customer id, and quantity
	 * 
	 */
	public void printAllBackOrders() {
		Iterator<Result> iterator = applianceStore.getAllBackOrders();
		System.out.println("Backorder ID | Appliance ID | Customer ID| Quantity");
		System.out.println("----------------------------------------------------" +
							"------------------------------------");
		while(iterator.hasNext()){
			Result result = iterator.next();
			System.out.println(result.getBackorderId() + " | " + result.getApplianceId() + " | " + result.getCustomerId()
			+ " | " + result.getQuantity());
		}
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
				listCustomers();
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
