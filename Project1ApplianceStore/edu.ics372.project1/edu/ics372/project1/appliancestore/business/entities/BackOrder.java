package edu.ics372.project1.appliancestore.business.entities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Back order class for creating back order objects.
 * 
 * @author Parker Talley.
 */
public class BackOrder implements Serializable {
    /**
     * For serialization/de-serialization of the data.
     */
    private static final long serialVersionUID = 1L;
    private static final String BACK_ORDER_STRING = "BCKORD";
    private static int idCounter = 0;

    /**
     * Stores the back order identification number.
     */
    private String id;
    /**
     * Stores reference to customer associated with repair plan.
     */
    private Customer customer;

    /**
     * Stores reference to appliance associated with repair plan.
     */
    private Appliance appliance;

    /**
     * Stores the quantity of appliance(s) on back order.
     */
    private int quantity;

    /**
     * Constructor for back orders.
     * 
     * @param customer Customer the customer placing the back order.
     * @param appliance Appliance the appliance on back order.
     * @param quantity int the number of appliance(s) on back order.
     */
    public BackOrder(Customer customer, Appliance appliance, int quantity) {
        this.setCustomer(customer);
        this.setAppliance(appliance);
        this.setQuantity(quantity);
        id = BACK_ORDER_STRING + idCounter++;
    }

    // Setters
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAppliance(Appliance appliance) {
        this.appliance = appliance;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public Appliance getAppliance() {
        return appliance;
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
