package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import java.util.function.Predicate;

public class FilteredApplianceIterator implements Iterator<Appliance> {
    private Appliance item;
    private String predicate;
    private Iterator<Appliance> iterator;

    /**
     * Sets the iterator and predicate fields and positions to the first item
     * that satisfies the predicate.
     * 
     * @param iterator the iterator to the list.
     * @param predicate specifies the test.
     */
    public FilteredApplianceIterator(Iterator<Appliance> iterator, String predicate) {
        this.predicate = predicate;
        this.iterator = iterator;
        getNextItem();
    }


    @Override
    public boolean hasNext() {
        return item != null;
    }

    @Override
    public Appliance next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such elment");
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
