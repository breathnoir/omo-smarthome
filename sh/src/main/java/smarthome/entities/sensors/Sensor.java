package smarthome.entities.sensors;

import smarthome.entities.Room;

import java.util.Random;

public abstract class Sensor {
    private String name;
    protected Room room;
    Random random = new Random();

    public Sensor(Room room, String name) {
        this.room = room;
        this.name = name;
    }

    public abstract void updateStat();

    public String getName() {
        return name;
    }

    public Room getRoom() {
        return room;
    }
}
