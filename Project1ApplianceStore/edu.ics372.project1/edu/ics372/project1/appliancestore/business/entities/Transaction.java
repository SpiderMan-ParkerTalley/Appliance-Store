package edu.ics372.project1.appliancestore.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A class for creating transaction objects.
 * 
 * @author Parker Talley.
 */
public abstract class Transaction implements Serializable {
    /**
     * For serialization/de-serialization of the data.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Stores the next transaction ID code.
     */
    private static int nextId = 1;
    
    /**
     * Stores the a time stamp that contain the time the transaction was created.
     */
    private Calendar timeStamp;
    
    /**
     * Stores the Transaction identification number.
     */
    private String id;

    /**
     * Stores reference to customer.
     */
    private Customer customer;

    /**
     * Stores the total of the transaction.
     */
    private double total;

    /**
     * Constructor for transaction.
     * @param customer Customer customer associated with transaction.
     * @param appliance Appliance appliance associated with transaction.
     * @param quantity int quantity of appliance in transaction.
     */
    public Transaction(Customer customer) {
        this.setCustomer(customer);
        this.timeStamp = new GregorianCalendar();
    }

    // Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setTimeStamp(Calendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public Calendar getTimeStamp() {
        return timeStamp;
    }

    public String getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    /**
     * Retrieves the next transaction ID.
     * @return nextId int the next transaction ID.
     */
    protected static int getNextId() {
        return ++nextId;
    }

    /**
     * Generates a string formate of time space based on the transactions time
     * of creation.
     * @return String time stamp in string formate.
     */
    public String getStringTimeStamp() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy");
        String stringStamp = formattedDate.format(this.timeStamp.getTime());
        return stringStamp;
    }

    /**
     * Saves the static fields.
     * @param output ObjectOutputStream
     */
    public static void save(ObjectOutputStream output) throws IOException {
        output.writeObject(nextId);
    }

    /**
    * Retrieves the static fields.
    */
    public static void retrieve(ObjectInputStream input) throws IOException, 
        ClassNotFoundException {
            nextId = (int) input.readObject();
    }
}
