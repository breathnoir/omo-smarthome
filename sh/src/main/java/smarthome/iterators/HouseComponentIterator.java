package smarthome.iterators;

import smarthome.HouseComponent;

public interface HouseComponentIterator {
    boolean hasNext();
    HouseComponent next();
}
