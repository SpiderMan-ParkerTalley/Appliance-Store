package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.BackOrder;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class SafeBackOrderIterator implements Iterator<Result>{
    private Iterator<BackOrder> iterator;
    private Result result = new Result();

    /**
     * The user of SafeIterator must supply an Iterator to BackOrder.
     * 
     * @param iterator Iterator<BackOrder>
     */
    public SafeBackOrderIterator(Iterator<BackOrder> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            result.setBackOrderFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }
}
