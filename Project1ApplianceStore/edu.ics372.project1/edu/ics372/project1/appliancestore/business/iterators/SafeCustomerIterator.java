package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Customer objects. The user should supply an iterator to Customer 
 * as the parameter to the constructor.
 * 
 * @author Parker Talley.
 */
public class SafeCustomerIterator implements Iterator<Result> {
    /**
     * Stores the customer iterator.
     */
    private Iterator<Customer> iterator;

    /**
     * The result object to be returned.
     */
    private Result result = new Result();
    
    /**
     * The user of SafeIterator must supply an Iterator to Customer.
     * 
     * @param iterator Iterator<Customer>
     */
    public SafeCustomerIterator(Iterator<Customer> iterator) {
        this.iterator = iterator;
    }
    
    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            Customer customer = iterator.next();
            result.setCustomerFields(customer);
            result.setTotalRevenueFromRepairPlans(customer.getRepairPlansTotalCost());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }
}
