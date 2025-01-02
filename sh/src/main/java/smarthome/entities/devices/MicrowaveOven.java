package smarthome.entities.devices;

import smarthome.entities.Room;

public class MicrowaveOven extends Device{
    public MicrowaveOven(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }
}
