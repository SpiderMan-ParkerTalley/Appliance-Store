package edu.ics372.project1.appliancestore.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Customer class for creating customer objects.
 * 
 * @author Parker Talley.
 */
public class Customer implements Serializable {
    /**
     * For serialization/de-serialization of the data.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Stores the customer ID code.
     */
    private static final String CUSTOMER_STRING = "C";
    
    // Fields
    /**
     * Stores customer ID counter.
     */
    private static int nextId;

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

    // Lists
    /**
     * A list containing all the customer's sale/purchase transactions.
     */
    private List<SaleTransaction> saleTransactions = new LinkedList<SaleTransaction>();
    
    /**
     * A list storing all the customer's active repair plans.
     */
    private List<RepairPlan> repairPlans = new LinkedList<RepairPlan>();

    /**
     * A list storing all the customer's active repair plans.
     */
    private List<RepairPlanTransaction> repairPlanTransactions = 
        new LinkedList<RepairPlanTransaction>();

    /**
     * Creates customer object.
     * 
     * @param name String name of customer.
     * @param address String address of customer.
     * @param phoneNumber String phone number of customer.
     */
    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        id = CUSTOMER_STRING + nextId++;
    }

    /**
     * Creates and adds a sale transaction to customer.
     * 
     * @param transaction SaleTransaction being added to customer..
     * @return boolean true if the sales transaction was successfully added, 
     * false if the sales transaction was not able to be added..
     */
    public boolean addSaleTransaction(SaleTransaction transaction) {
        saleTransactions.add(transaction);
        return true;
    }

    /**
     * Computes customer's total amount paid for sales.
     * 
     * @return double sale transaction total cost.
     */
    public double getSalesTotalCost() {
        double saleTransactionsTotalCost = 0.0;
        for(Iterator<SaleTransaction> iterator = saleTransactions.iterator(); 
            iterator.hasNext();) {
                SaleTransaction saleTransaction = iterator.next();
                saleTransactionsTotalCost += saleTransaction.getTotal();
        }
        
        return saleTransactionsTotalCost;
    }

    /**
     * Creates and adds a repair plan to customer.
     * 
     * @param appliance Appliance appliance to be associated with repair plan.
     * @return boolean true if the repair plan was added, always returns true in
     * current iteration.
     */
    public boolean addRepairPlan(ApplianceWithRepairPlan appliance) {
        repairPlans.add(new RepairPlan(this, appliance));
        return true;
    }

    /**
     * Removes a repair plan from customer.
     * 
     * @param repairPlan RepairPlan the repair plan to be removed.
     * @return boolean true if the repair plan was removed, always returns true 
     * in current iteration.
     */
    public boolean removeRepairPlan(RepairPlan repairPlan) {
        repairPlans.remove(repairPlan);
        return true;
    }

    /**
     * Charges the customer for all active repair plans.
     */
    public double chargeRepairPlans() {
        // Stores the total amount charged for repair plans.
        double amountCharged = 0.0;
        for(Iterator<RepairPlan> iterator = repairPlans.iterator(); iterator.hasNext();) {
            RepairPlan repairPlan = iterator.next();
            // Creates new repair plan transaction.
            RepairPlanTransaction repairPlanTransaction = new RepairPlanTransaction(this, repairPlan.getAppliance());
            // Adds repair plan transaction to customer's transactions.
            repairPlanTransactions.add(repairPlanTransaction);
            // Computes total amount charged in repair plans.
            amountCharged += repairPlanTransaction.getTotal();
        }
        // Returns the amount charged to the customer.
        return amountCharged;
    }

    /**
     * Computes customer's total amount paid for repair plans.
     * 
     * @return double repair plan total cost.
     */
    public double getRepairPlansTotalCost() {
        double repairPlansTotalCost = 0.0;
        for(Iterator<RepairPlanTransaction> iterator = repairPlanTransactions.iterator(); 
            iterator.hasNext();) {
                RepairPlanTransaction repairPlanTransaction = iterator.next();
                repairPlansTotalCost += repairPlanTransaction.getTotal();
        }
        return repairPlansTotalCost;
    }

    /**
     * Searches a customer for a repair plan matching the applianceId.
     * 
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

    /**
     * Retrieves a customer's repair plan transaction iterator.
     * 
     * @return Iterator<RepairPlan> iterator.
     */
	public Iterator<RepairPlan> getRepairPlanIterator() {
		return repairPlans.iterator();
	}

    /**
     * Retrieves a customer's sales transaction iterator.
     * 
     * @return Iterator<SaleTransaction> iterator.
     */
	public Iterator<SaleTransaction> getSalesTransactionIterator() {
		return saleTransactions.iterator();
	}

    /**
     * Checks if a customer has one or more repair plans.
     * 
     * @return boolean true customer has repair plan, false otherwise.
     */
    public boolean hasRepairPlan() {
        if (!this.repairPlans.isEmpty()) {
            return true;
        }
        return false;
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

    public String getId() {
        return id;
    }

    /**
     * Saves the static fields in Customer class.
     * 
     * @param output ObjectOutputStream object.
     */
    public static void save(ObjectOutputStream output) throws IOException {
        output.writeObject(nextId);
    }

    /**
    * Retrieves the static fields in Customer class.
    */
    public static void retrieve(ObjectInputStream input) throws IOException, 
        ClassNotFoundException {
            nextId = (int) input.readObject();
    }    
}
