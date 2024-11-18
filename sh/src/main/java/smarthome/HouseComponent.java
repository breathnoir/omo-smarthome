package smarthome;

import smarthome.iterators.HouseComponentIterator;

public interface HouseComponent {
    void accept(Visitor visitor);
    HouseComponentIterator iterator();
}
