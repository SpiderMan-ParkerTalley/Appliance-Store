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
