package smarthome.entities.sensors;

import smarthome.entities.Room;

public class Sensor {
    private String name;
    private Room room;
    public Sensor(Room room, String name) {
        this.room = room;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }
}
