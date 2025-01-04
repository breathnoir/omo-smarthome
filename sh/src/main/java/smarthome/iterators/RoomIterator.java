package smarthome.iterators;

import smarthome.reports.HouseComponent;

import java.util.List;

public class RoomIterator implements HouseComponentIterator{

    private final List<HouseComponent> devices;
    private int position = 0;

    public RoomIterator(List<HouseComponent> devices) {
        this.devices = devices;
    }

    @Override
    public boolean hasNext() {
        return position < devices.size();
    }

    @Override
    public HouseComponent next() {
        return devices.get(position++);
    }
}
