package edu.ics372.project1.appliancestore.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String TRANSACTION_STRING = "TRANS";
    private static int idCounter = 1;
    private Calendar timeStamp;

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
        this.timeStamp = new GregorianCalendar();
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

    public Calendar getTimeStamp() {
        return this.timeStamp;
    }

    public String getStringStamp() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MMM-yyy");
        String stringStamp = formattedDate.format(this.timeStamp.getTime());
        return stringStamp;
    }

    public void setTimeStamp(Calendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Returns the TRANSACTION_STRING field to help with the matches method.
     * @return TRANSACTION_STRING field as a String
     */
    public String getTransactionString() {
        return TRANSACTION_STRING;
    }
    /**
     * Tests if the classes are the same by testing the static TRANSACTION_STRING
     * field. If they are the same, returns true. If they are not, returns false.
     * The assertion we make so that this holds true is that all Transaction type
     * classes have the same TRANSACTION_STRING field, as it is static.
     * @param transaction The transaction being compared.
     * @return true if the TRANSACTION_STRING fields match, else false.
     */
    public boolean matches(Transaction transaction) {
        if(TRANSACTION_STRING.equals(transaction.getTransactionString())) {
            return true;
        } else {
            return false;
        }
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
}
