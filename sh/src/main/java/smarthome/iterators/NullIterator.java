package smarthome.iterators;

import smarthome.reports.HouseComponent;

/**
 * The NullIterator class is an implementation of the HouseComponentIterator interface
 * that represents an iterator with no elements. This class is used as a
 * default implementation for cases where iteration over components is not applicable.
 */
public class NullIterator implements HouseComponentIterator{
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public HouseComponent next() {
        return null;
    }
}
