package smarthome.entities;

import smarthome.reports.HouseComponent;
import smarthome.reports.Visitor;
import smarthome.iterators.FloorIterator;
import smarthome.iterators.HouseComponentIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<HouseComponent> getRooms() {
        return rooms;
    }
    public List<Room> getAllRooms() {
        return rooms.stream().map(Room.class::cast).collect(Collectors.toList());
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    @Override
    public void acceptVisitor(Visitor visitor) {
        visitor.visitFloor(this);
        HouseComponentIterator iterator = iterator();
        while (iterator.hasNext()) {
            HouseComponent room = iterator.next();
            room.acceptVisitor(visitor);
        }
    }

    @Override
    public HouseComponentIterator iterator() {
        return new FloorIterator(rooms);
    }
}
