package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Transaction;
import java.util.function.Predicate;

/**
 * This class implements the Iterator interface to iterate only on appliances
 * that satisfy a certain predicate.
 * 
 * @author Parker Talley
 */
public class FilteredTransactionIterator implements Iterator<Transaction> {
    /**
     * Stores Transaction item, the next item to be returned.
     */
    private Transaction item;

    /**
     * Store the transaction predicate, what is currently needed to be found.
     */
    private Predicate<Transaction> predicate;

    /**
     * Stores the iterator for transaction.
     */
    private Iterator<Transaction> iterator;

    /**
     * Sets the iterator and predicate fields and positions to the first item
     * that satisfies the predicate.
     * 
     * @param iterator Iterator<Transaction> the iterator to the list.
     * @param predicate Predicate<Transaction> specifies the test.
     */
    public FilteredTransactionIterator(Iterator<Transaction> iterator, Predicate<Transaction> predicate) {
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
            throw new NoSuchElementException("No such element");
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
