package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Customer;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class SafeCustomerIterator implements Iterator<Result> {
    private Iterator<Customer> iterator;
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
            result.setCustomerFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }
}
