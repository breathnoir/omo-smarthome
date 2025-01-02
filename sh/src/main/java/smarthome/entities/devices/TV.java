package smarthome.entities.devices;

import smarthome.entities.Room;

public class TV extends Device {
    public TV(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }
}
