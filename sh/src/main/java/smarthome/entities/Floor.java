package smarthome.entities;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private String name;
    private List<Room> rooms = new ArrayList<>();

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
}
