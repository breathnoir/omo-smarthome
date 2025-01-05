package smarthome.iterators;

import smarthome.reports.HouseComponent;

/**
 * An interface for iterating over components within a house structure.
 * This abstraction supports traversing composite objects such as houses, floors, and rooms.
 * Classes implementing this interface provide specific iteration logic for various elements
 * of the house hierarchy.
 */
public interface HouseComponentIterator {
    boolean hasNext();
    HouseComponent next();
}
