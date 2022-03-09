package edu.ics372.project1.appliancestore.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Customer implements Serializable {
    // Fields
    private static int idCounter;
    private static final String CUSTOMER_STRING = "C";
    /**
     * Stores the customer's identification number.
     */
    private String id;
    
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

    /**
     * Stores the total amount the customer has paid in repair plans.
     */
    private double repairPlansTotalCost;

    /**
     * 
     */
    private double transactionTotalCost;

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
        id = CUSTOMER_STRING + idCounter++;
    }

    /**
     * Creates and adds a transaction to customer.
     * @param appliance Appliance appliance 
     * @param quantity int quantity of appliance being purchases.
     * @return boolean true if the transaction was sucessfully added.
     */
    public boolean addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transactionTotalCost += transaction.getAppliance().getPrice() * transaction.getQuantity();
        return true;
    }

    /**
     * Creates and adds a repair plan to customer.
     * @param appliance Appliance appliance to be assoicated with repair plan.
     * @return boolean true if repair plan was sucessfully added.
     */
    public boolean addRepairPlan(Appliance appliance) {
        repairPlans.add(new RepairPlan(this, appliance));
        return true;
    }

    /**
     * Removes a repair plan from customer.
     * @param repairPlan RepairPlan the repair plan to be removed.
     * @return boolean true if the repair plan was removed, false if no repair 
     * plan was removed.
     */
    public boolean removeRepairPlan(RepairPlan repairPlan) {
        repairPlans.remove(repairPlan);
        return true;
    }

    /**
     * Charges the customer for all active repair plans.
     */
    public void chargeRepairPlans() {
        for(Iterator<RepairPlan> iterator = repairPlans.iterator(); iterator.hasNext();) {
            RepairPlan repairPlan = iterator.next();
            repairPlansTotalCost += repairPlan.getCost();
            // TODO: Might need to add the repair plan as a transaction. Not sure..
        }
    }

    /**
     * Getter for repair plan list iterator.
     * @return iterator for repair plans.
     */
    public Iterator<RepairPlan> getRepairPlans() {
		return repairPlans.iterator();
	}

    /**
     * Searches a customer for a repair plan matching the applianceId.
     * @param applianceId String appliance id for search.
     * @return RepairPlan repair plan if a match is found, otherwise null.
     */
    public RepairPlan searchRepairPlan(String applianceId) {
        for (Iterator<RepairPlan> iterator = repairPlans.iterator(); iterator.hasNext();) {
            RepairPlan repairPlan = iterator.next();
            if(repairPlan.getCustomer().id.compareToIgnoreCase(this.id) == 0 && 
            repairPlan.getAppliance().getId().compareToIgnoreCase(applianceId) == 0) {
                return repairPlan;
            }
        }
        return null;
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
        return id;
    }

    public double getRepairPlansTotalCost() {
        return repairPlansTotalCost;
    }

    public double getTransactionTotalCost() {
        return transactionTotalCost;
    }

    /**
     * Checks if a customer has one or more repair plans.
     * @return boolean true customer has repair plan, false otherwise.
     */
    public boolean hasRepairPlan() {
        if (!this.repairPlans.isEmpty()) {
            return true;
        }
        return false;
    }

    public String getInformation() {
        String customerInfo = "Member name " + name + " address " + address + 
            " id " + id + " phone number " + phoneNumber + " acount balance " + 
            accountBalance + " has repair plan: ";
        if(this.hasRepairPlan()) {
            customerInfo.concat("true");
        } else {
            customerInfo.concat("false");
        }
        return customerInfo;
    }

    /**
     * Saves the static idCounter.
     * @param output
     */
    public static void save(ObjectOutputStream output) throws IOException {
        output.writeObject(idCounter);
    }
    /**
    * Retrieves the static id counter.
    */
    public static void retrieve(ObjectInputStream input) throws IOException, 
                            ClassNotFoundException {
        idCounter = (int) input.readObject();
    }

    //TODO Remove after testing
    /**
     * Sets customerId - FOR TESTING ONLY
     */
    public void setCustomerId(String custID) {
        this.id = custID;
    }
}
