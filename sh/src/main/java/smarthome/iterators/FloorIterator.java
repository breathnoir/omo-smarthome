package smarthome.iterators;

import smarthome.reports.HouseComponent;

import java.util.List;

public class FloorIterator implements HouseComponentIterator{
    private final List<HouseComponent> rooms;
    private int position = 0;

    public FloorIterator(List<HouseComponent> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean hasNext() {
        return position < rooms.size();
    }

    @Override
    public HouseComponent next() {
        return rooms.get(position++);
    }
}
