package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.RepairPlan;
import edu.ics372.project1.appliancestore.business.facade.Result;

/**
 * This Iterator implementation is tailor-made to supply a "read-only" version
 * of RepairPlan objects. The user should supply an iterator to RepairPlan 
 * as the parameter to the constructor.
 * 
 * @author Parker Talley.
 */
public class SafeRepairPlanIterator implements Iterator<Result> {
    /**
     * Stores the repair plan iterator.
     */
    private Iterator<RepairPlan> iterator;
    /**
     * The result object to be returned.
     */
    private Result result = new Result();

    /**
     * The user of SafeIterator must supply an Iterator to RepairPlan.
     * 
     * @param iterator Iterator<RepairPlan> the iterator to the list.
     */
    public SafeRepairPlanIterator(Iterator<RepairPlan> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            result.setRepairPlanFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }
}
