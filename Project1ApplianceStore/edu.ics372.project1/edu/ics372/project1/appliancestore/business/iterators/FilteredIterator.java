package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Transaction;
import java.util.function.Predicate;

//TODO Comments for class and author
public class FilteredIterator implements Iterator<Transaction> {
    private Transaction item;
    private Predicate<Transaction> predicate;
    private Iterator<Transaction> iterator;

    /**
     * Sets the iterator and predicate fields and positions to the first item
     * that satisfies the predicate.
     * 
     * @param iterator the iterator to the list.
     * @param predicate specifies the test.
     */
    public FilteredIterator(Iterator<Transaction> iterator, Predicate<Transaction> predicate) {
        this.predicate = predicate;
        this.iterator = iterator;
        getNextItem();
    }


    @Override
    public boolean hasNext() {
        return item != null;
    }

    @Override
    public Transaction next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No such elment");
        }
        Transaction returnValue = item;
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
            if (predicate.test(item)) {
                return;
            }
        }
        // Memory garbage collection.
        item = null;
    }
    
}
