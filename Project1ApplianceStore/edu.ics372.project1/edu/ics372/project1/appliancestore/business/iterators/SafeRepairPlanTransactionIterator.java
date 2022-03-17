package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.RepairPlanTransaction;
import edu.ics372.project1.appliancestore.business.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of RepairPlanTransaction objects. The user should supply an iterator to 
 * RepairPlanTransaction as the parameter to the constructor.
 * 
 * @author Parker Talley.
 */
public class SafeRepairPlanTransactionIterator implements Iterator<Result> {
    /**
     * Stores the repair plan transaction iterator.
     */
    private Iterator<RepairPlanTransaction> iterator;

    /**
     * The result object to be returned.
     */
    private Result result;

    /**
     * The user of SafeIterator must supply an Iterator of RepairPlanTransaction.
     * 
     * @param iterator Iterator<RepairPlanTransaction> the iterator to the list.
     */
    public SafeRepairPlanTransactionIterator(Iterator<RepairPlanTransaction> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            result.setRepairPlanTransactionFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }

    
}
