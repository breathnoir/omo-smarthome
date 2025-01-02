package smarthome.entities.devices;

import smarthome.entities.Room;

public class Blinds extends Device implements Observer{
    public Blinds(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }

    @Override
    public void update() {
        double windSpeed = getRoom().getWindSpeed();
    }
}
