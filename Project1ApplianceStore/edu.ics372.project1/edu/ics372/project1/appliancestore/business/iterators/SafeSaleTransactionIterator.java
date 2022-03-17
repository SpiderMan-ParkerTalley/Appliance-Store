package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.SaleTransaction;
import edu.ics372.project1.appliancestore.business.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of SaleTransaction objects. The user should supply an iterator to 
 * SaleTransaction as the parameter to the constructor.
 * 
 * @author Parker Talley.
 */
public class SafeSaleTransactionIterator implements Iterator<Result> {
    /**
     * Stores the sale transaction iterator.
     */
    private Iterator<SaleTransaction> iterator;

    /**
     * The result object to be returned.
     */
    private Result result;

    /**
     * The user of SafeIterator must supply an Iterator to SaleTransaction.
     * 
     * @param iterator Iterator<SaleTransaction> the iterator to the list.
     */
    public SafeSaleTransactionIterator(Iterator<SaleTransaction> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            result.setSaleTransactionFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }

    
}
