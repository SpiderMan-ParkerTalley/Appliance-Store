package edu.ics372.project1.appliancestore.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.project1.appliancestore.business.entities.Customer;

public class CustomerList implements Iterable<Customer>, Serializable {

    private static final long serialVersionUID = 1L;
	private List<Customer> customers = new LinkedList<Customer>();
	private static CustomerList customerList;

	private CustomerList() {

	}

    public static CustomerList getInstance(){
        if (customerList == null){
            customerList = new CustomerList();
        }
        return customerList;
    }

    /**
     * Checks whether a customer with a given customer id exists.
     * 
     * @param customerId String the id of the customer.
     * @return Customer customer object if found, null otherwise.
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

    /**
     * Calls the hasRepairPlans method in customer to see if the customer has a repair plan.
     * if they do, it adds it to the List of customers to be returned.
     * @return a LinkedList of Customer objects with a repair plan.
     */
    public List<Customer> getAllCustomersInRepairPlan() {
        List<Customer> customersWithRepairPlans = new LinkedList<Customer>();
        for (Customer customer : customers) {
            if (customer.hasRepairPlan()) {
                customersWithRepairPlans.add(customer);
            }
        }
        return customersWithRepairPlans;
        
    }
    @Override
    public Iterator<Customer> iterator() {
        return customers.iterator();
    } 

    
}
