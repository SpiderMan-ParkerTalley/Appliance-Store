package edu.ics372.project1.appliancestore.business.entities;

public class Transaction {
    private static final String TRANSACTION_STRING = "TRANS";
    private static int idCounter = 1;

    /**
     * Stores reference to customer.
     */
    private Customer customer;

    /**
     * Stores reference to appliance.
     */
    private Appliance appliance;

    /**
     * Stores the number of appliance purchased.
     */
    private int quantity;

    /**
     * Stores transaction code.
     */
    private String code;

    /**
     * Constructor for transaction.
     * @param customer Customer customer associated with transction.
     * @param appliance Appliance appliance associated with transaction.
     * @param quantity int quantity of appliance in transaction.
     */
    public Transaction(Customer customer, Appliance appliance, int quantity) {
        this.setCustomer(customer);
        this.setAppliance(appliance);
        this.setQuantity(quantity);
        setCode(TRANSACTION_STRING + idCounter++);
    }

    // Setters
    public void setCode(String code) {
        this.code = code;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public Appliance getAppliance() {
        return appliance;
    }

    public Customer getCustomer() {
        return customer;
    }
}
