package smarthome.iterators;

import smarthome.reports.HouseComponent;

public interface HouseComponentIterator {
    boolean hasNext();
    HouseComponent next();
}
