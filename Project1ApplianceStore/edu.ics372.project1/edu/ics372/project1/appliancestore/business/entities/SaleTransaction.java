package edu.ics372.project1.appliancestore.business.entities;

/**
 * A class for creating sale transaction objects.
 * 
 * @author Parker Talley.
 */
public class SaleTransaction extends Transaction {
    /**
     * Stores the sale transaction ID code.
     */
    private static final String SALES_TRANSACTION = "SALE-TRAN";
    
    /**
     * Stores the number of appliance purchased.
     */
    private int quantity;

    /**
     * Stores reference to appliance.
     */
    private Appliance appliance;

    /**
     * 
     * @param customer Customer 
     * @param appliance Appliance
     * @param quantity int
     */
    public SaleTransaction(Customer customer, Appliance appliance, int quantity) {
        super(customer);
        this.appliance = appliance;
        this.quantity = quantity;
        this.setId(generateId());
        this.setTotal(appliance.getPrice() * quantity);
    }

    /**
     * Generates the next sales transaction ID.
     * @return String the next sales transaction ID.
     */
    private String generateId() {
        return SALES_TRANSACTION + getNextId();
    }

    // Getters
    public int getQuantity() {
        return quantity;
    }

    public Appliance getAppliance() {
        return appliance;
    }
}
