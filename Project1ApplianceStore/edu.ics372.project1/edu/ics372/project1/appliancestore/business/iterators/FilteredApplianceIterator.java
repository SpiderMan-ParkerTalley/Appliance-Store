package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
<<<<<<< Updated upstream
import java.util.function.Predicate;

public class FilteredApplianceIterator implements Iterator<Appliance> {
    private Appliance item;
    private String predicate;
    private Iterator<Appliance> iterator;
=======
import edu.ics372.project1.appliancestore.business.entities.Transaction;
import java.util.function.Predicate;

public class FilteredApplianceIterator implements Iterator<Appliance> {
    private Transaction item;
    private Predicate<Transaction> predicate;
    private Iterator<Transaction> iterator;
>>>>>>> Stashed changes

    /**
     * Sets the iterator and predicate fields and positions to the first item
     * that satisfies the predicate.
     * 
     * @param iterator the iterator to the list.
     * @param predicate specifies the test.
     */
<<<<<<< Updated upstream
    public FilteredApplianceIterator(Iterator<Appliance> iterator, String predicate) {
=======
    public FilteredApplianceIterator(Iterator<Appliance> iterator, Predicate<Transaction> predicate) {
>>>>>>> Stashed changes
        this.predicate = predicate;
        this.iterator = iterator;
        getNextItem();
    }


    @Override
    public boolean hasNext() {
        return item != null;
    }

    @Override
<<<<<<< Updated upstream
    public Appliance next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such elment");
        }
        Appliance returnValue = item;
=======
    public Transaction next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such elment");
        }
        Transaction returnValue = item;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
            if (predicate.contains(item.getId())) {
=======
            if (predicate.test(item)) {
>>>>>>> Stashed changes
                return;
            }
        }
        // Memory garbage collection.
        item = null;
    }
    
}
