package edu.ics372.project1.appliancestore.business.entities;

import java.util.LinkedList;
import java.util.List;

public class Customer {
    // stupid comment
    // Fields
    private static int idCounter;
    private static final String CUSTOMER_STRING = "C";
    /**
     * Stores the customer's identification number.
     */
    private String customerId;
    
    /**
     * Stores the customer's name.
     */
    private String name;

    /**
     * Stores the customer's address.
     */
    private String address;

    /**
     * Stores the customer's phone number.
     */
    private String phoneNumber;

    /**
     * Stores the customer's account balance.
     */
    private double accountBalance;

    private double repairPlansTotalCost; // not sure if this should be kept
    private double transactionTotalCost; // not sure if this should be kept

    // Lists
    /**
     * A list containing all the customer's transactions.
     */
    private List<Transaction> transactions = new LinkedList<Transaction>();
    
    /**
     * A list storing all the customer's active repair plans.
     */
    private List<RepairPlan> repairPlans = new LinkedList<RepairPlan>();

    // Constructor
    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.customerId = CUSTOMER_STRING + idCounter++;
    }

    /**
     * Creates and adds a transaction to customer.
     * @param appliance Appliance appliance 
     * @param quantity int
     * @return boolean true if the transaction was sucessfully added.
     */
    public boolean addTransaction(Appliance appliance, int quantity) {
        transactions.add(new Transaction(this, appliance, quantity));
        transactionTotalCost =+ appliance.getPrice() * quantity;
        return true;
    }

    /**
     * Creates and adds a repair plan to customer.
     * @param appliance Appliance
     * @return boolean true if repair plan was sucessfully added.
     */
    public boolean addRepairPlan(Appliance appliance) {
        repairPlans.add(new RepairPlan(this, appliance));
        return true;
    }

    public void chargeRepairPlans() {
        for()
    }
 
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public double getAccountBalance() {
        return accountBalance;
    }

    public String getId() {
        return customerId;
    }


}
