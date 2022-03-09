package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.RepairPlan;
import edu.ics372.project1.appliancestore.business.facade.Result;

public class SafeRepairPlanIterator implements Iterator<Result> {
    private Iterator<RepairPlan> iterator;
    private Result result = new Result();

    /**
     * The user of SafeIterator must supply an Iterator to RepairPlan.
     * 
     * @param
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
