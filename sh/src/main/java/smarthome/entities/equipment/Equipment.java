package smarthome.entities.equipment;

import smarthome.entities.Room;

/**
 * Represents equipment within a smart home system.
 * Equipment instances have a name and are optionally associated with a room.
 */
public class Equipment {
    private String name;
    Room room = null;

    public Equipment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }
}
