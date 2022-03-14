package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.RepairPlanTransaction;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class SafeRepairPlanTransactionIterator implements Iterator<Result> {
    private Iterator<RepairPlanTransaction> iterator;
    private Result result;

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
