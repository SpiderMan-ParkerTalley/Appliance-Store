package edu.ics372.project1.appliancestore.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

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



	public static void main(String args[]) {
		
	}
}
