package edu.ics372.project1.appliancestore.business.entities;

public class BackOrder {
    private static int idCounter;

    /**
     * Stores the back order identification number.
     */
    private String backOrderID;
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
     * 
     * @param customer Customer 
     * @param appliance Appliance
     * @param quantity int 
     */
    public BackOrder(Customer customer, Appliance appliance, int quantity) {
        this.customer = customer;
        this.appliance = appliance;
        this.quantity = quantity;
        backOrderID = "BCKORD" + idCounter++;
    }

}
