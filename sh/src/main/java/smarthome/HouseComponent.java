package smarthome;

import smarthome.iterators.HouseComponentIterator;

public interface HouseComponent {
    void acceptVisitor(Visitor visitor);
    HouseComponentIterator iterator();
}
