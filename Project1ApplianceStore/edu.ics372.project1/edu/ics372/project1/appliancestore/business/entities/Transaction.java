package edu.ics372.project1.appliancestore.business.entities;

public class Transaction {
    private static final String TRANSACTION_STRING = "TRANS";
    private static int idCounter = 1;
    private Customer customer;
    private Appliance appliance;
    private int quantity;
    private String code;

    public Transaction(Customer customer, Appliance appliance, int quantity) {
        this.setCustomer(customer);
        this.setAppliance(appliance);
        this.setQuantity(quantity);
        setCode(TRANSACTION_STRING + idCounter++);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Appliance getAppliance() {
        return appliance;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
