package smarthome.iterators;

import smarthome.reports.HouseComponent;

import java.util.List;

public class HouseIterator implements HouseComponentIterator{

    private final List<HouseComponent> floors;
    private int position = 0;

    public HouseIterator(List<HouseComponent> floors) {
        this.floors = floors;
    }

    @Override
    public boolean hasNext() {
        return position < floors.size();
    }

    @Override
    public HouseComponent next() {
        return floors.get(position++);
    }
}
