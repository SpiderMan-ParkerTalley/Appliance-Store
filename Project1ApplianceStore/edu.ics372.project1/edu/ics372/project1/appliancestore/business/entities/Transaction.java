package edu.ics372.project1.appliancestore.business.entities;

public class Transaction {
    private static final String TRANSACTION_STRING = "TRANS";
    private static int idCounter = 1;
    private Customer customer;
    private Appliance appliance;
    private int quantity;
    private String code;

    public Transaction(Customer customer, Appliance appliance, int quantity) {
        this.customer = customer;
        this.appliance = appliance;
        this.quantity = quantity;
        code = TRANSACTION_STRING + idCounter++;
    }
}
