package smarthome.entities.devices;

import smarthome.entities.Room;

public class PC extends Device {
    public PC(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }
}
