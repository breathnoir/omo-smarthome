package smarthome.entities.equipment;

import smarthome.entities.Room;

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
