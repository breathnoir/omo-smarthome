package smarthome.iterators;

import smarthome.reports.HouseComponent;

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
