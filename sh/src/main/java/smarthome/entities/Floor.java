package smarthome.entities;

import smarthome.HouseComponent;
import smarthome.Visitor;
import smarthome.iterators.FloorIterator;
import smarthome.iterators.HouseComponentIterator;

import java.util.ArrayList;
import java.util.List;

public class Floor implements HouseComponent {
    private String name;
    private List<HouseComponent> rooms = new ArrayList<>();

    public Floor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitFloor(this);
        HouseComponentIterator iterator = iterator();
        while (iterator.hasNext()) {
            HouseComponent room = iterator.next();
            room.accept(visitor);
        }
    }

    @Override
    public HouseComponentIterator iterator() {
        return new FloorIterator(rooms);
    }
}
