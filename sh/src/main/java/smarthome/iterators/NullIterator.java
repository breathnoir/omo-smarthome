package smarthome.iterators;

import smarthome.HouseComponent;

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
