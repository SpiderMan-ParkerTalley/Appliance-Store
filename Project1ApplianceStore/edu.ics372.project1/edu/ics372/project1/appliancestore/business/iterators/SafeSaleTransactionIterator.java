package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.SaleTransaction;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class SafeSaleTransactionIterator implements Iterator<Result> {
    private Iterator<SaleTransaction> iterator;
    private Result result;

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
