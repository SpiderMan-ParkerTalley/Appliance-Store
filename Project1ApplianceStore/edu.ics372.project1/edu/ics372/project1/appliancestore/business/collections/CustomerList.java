package edu.ics372.project1.appliancestore.business.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.Customer;

public class CustomerList {

    private static final long serialVersionUID = 1L;
	private List<Customer> customers = new LinkedList<Customer>();
	private static CustomerList customerList;

	private CustomerList() {

	}

    public static CustomerList getInstance(){
        if(customerList == null){
            customerList = new CustomerList();
        }
        return customerList;
    }

    /**
     * Checks whether a customer with a gven customer id exists.
     * 
     * @param customerId String the id of the customer.
     * @return boolean true if customer exist; false otherwise.
     */
    public Customer search(String customerId) {
        for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
            Customer customer = iterator.next();
            if(customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Inserts a customer into the collection.
     * 
     * @param customer Customer the customer to be inserted.
     * @return boolean true if the customer could be inserted.
     */
    public boolean insertCustomer(Customer customer) {
		customers.add(customer);
		return true;
	}
}
