package smarthome.entities.devices;

import smarthome.entities.Room;

public class Humidifier extends Device implements Observer{
    public Humidifier(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }

    @Override
    public void update() {
        double humidity = getRoom().getHumidity();
    }
}
