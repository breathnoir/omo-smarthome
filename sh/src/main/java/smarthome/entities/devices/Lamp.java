package smarthome.entities.devices;

import smarthome.entities.Room;

public class Lamp extends Device implements Observer{
    public Lamp(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }

    @Override
    public void update() {

    }
}