package smarthome.entities.builders;

import smarthome.entities.Floor;
import smarthome.entities.Room;

/**
 * A builder class for creating and configuring a Floor object. This class
 * allows adding rooms to the floor incrementally and provides a method to
 * retrieve the constructed Floor instance.
 */
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
