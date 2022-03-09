package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.plaf.basic.BasicComboBoxUI.ItemHandler;

import edu.ics372.project1.appliancestore.business.entities.Transaction;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class SafeTransactionIterator implements Iterator<Result> {
    private Iterator<Transaction> iterator;
    private Result result;

    public SafeTransactionIterator(Iterator<Transaction> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            result.setTransactionFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }

    
}
