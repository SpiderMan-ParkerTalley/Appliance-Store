package edu.ics372.project1.appliancestore.business.entities;

public class SaleTransaction extends Transaction {
    private static final String SALES_TRANSACTION = "SALE-TRAN";
    
    /**
     * Stores the number of appliance purchased.
     */
    private int quantity;

    /**
     * Stores reference to appliance.
     */
    private Appliance appliance;

    public SaleTransaction(Customer customer, Appliance appliance, int quantity) {
        super(customer);
        this.appliance = appliance;
        this.quantity = quantity;
        this.setId(generateId());
        this.setTotal(appliance.getPrice() * quantity);
    }

    public String generateId() {
        return SALES_TRANSACTION + getNextId();
    }

    // Setters
    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }

    // Getters
    public int getQuantity() {
        return quantity;
    }

    public Appliance getAppliance() {
        return appliance;
    }
}
