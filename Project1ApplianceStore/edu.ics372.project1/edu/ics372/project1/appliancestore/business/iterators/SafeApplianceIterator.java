package edu.ics372.project1.appliancestore.business.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.project1.appliancestore.business.entities.Appliance;
import edu.ics372.project1.appliancestore.business.facade.Result;

//TODO Comments for class and author
public class SafeApplianceIterator implements Iterator<Result> {
    private Iterator<Appliance> iterator;
    private Result result = new Result();

    /**
     * The user of SafeIterator must supply an Iterator to Appliance.
     * 
     * @param iterator Iterator<Appliance>
     */
    public SafeApplianceIterator(Iterator<Appliance> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            result.setApplianceFields(iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }
}
