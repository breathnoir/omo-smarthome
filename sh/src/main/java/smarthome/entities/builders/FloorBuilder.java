package smarthome.entities.builders;

import smarthome.entities.Floor;
import smarthome.entities.Room;

public class FloorBuilder {
    private Floor floor;

    public FloorBuilder(String floorName) {
        floor = new Floor(floorName);
    }

    public FloorBuilder addRoom(Room room) {
        floor.addRoom(room);
        return this;
    }

    public Floor build() {
        return floor;
    }
}
