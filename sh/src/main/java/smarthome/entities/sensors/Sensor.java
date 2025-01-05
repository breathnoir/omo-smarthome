package smarthome.entities.sensors;

import smarthome.entities.Room;

import java.util.Random;

/**
 * Represents an abstract sensor used for monitoring and updating specific environmental or
 * status-related attributes of a room in a smart home system.
 * This class serves as a base for different types of sensors such as temperature, humidity,
 * motion, light, and wind sensors.
 */
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
