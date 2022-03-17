package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of Appliance objects. The user should supply an iterator to Appliance/Model 
 * as the parameter to the constructor.
 * 
 * @author Parker Talley.
 */
public class SafeApplianceIterator implements Iterator<Result> {
    /**
     * Stores the appliance iterator.
     */
    private Iterator<Appliance> iterator;

    /**
     * The result object to be returned.
     */
    private Result result = new Result();

    /**
     * The user of SafeIterator must supply an Iterator to Appliance.
     * 
     * @param iterator Iterator<Appliance> the iterator to the list.
     */
    public SafeApplianceIterator(Iterator<Appliance> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            result.setApplianceFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }
}
