package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Appliance;

/**
 * This class implements the Iterator interface to iterate only on appliances
 * that satisfy a certain predicate.
 * 
 * @author Parker Talley
 */
public class FilteredApplianceIterator implements Iterator<Appliance> {
    /**
     * Stores Appliance item, the next item to be returned.
     */
    private Appliance item;

    /**
     * Store the string predicate, what is currently needed to be found.
     */
    private String predicate;

    /**
     * Stores the iterator for appliance.
     */
    private Iterator<Appliance> iterator;

    /**
     * Sets the iterator and predicate fields and positions to the first item
     * that satisfies the predicate.
     * 
     * @param iterator Iterator<Appliance> the iterator to the list.
     * @param predicate String specifies the test.
     */
    public FilteredApplianceIterator(Iterator<Appliance> iterator, String predicate) {
        this.predicate = predicate; // sub string
        this.iterator = iterator; // iterator
        getNextItem();
    }

    @Override
    public boolean hasNext() {
        return item != null;
    }

    @Override
    public Appliance next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such element");
        }
        Appliance returnValue = item;
        getNextItem();
        return returnValue;
    }

    /**
     * This method searches for the next item that satisfies the predicate. If 
     * none is found, the item field is set to null.
     */
    private void getNextItem() {
        while (iterator.hasNext()) {
            item = iterator.next();
            if (item.getId().contains(predicate)) {
                return;
            }
        }
        // Memory garbage collection.
        item = null;
    }
    
}
