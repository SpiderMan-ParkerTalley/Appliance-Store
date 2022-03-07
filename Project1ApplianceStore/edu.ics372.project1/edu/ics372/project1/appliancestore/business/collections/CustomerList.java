package edu.ics372.project1.appliancestore.business.collections;

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

    public boolean insertCustomer(Customer customer) {
		customers.add(customer);
		return true;
	}
}
