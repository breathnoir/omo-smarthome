package smarthome.entities.devices;

import smarthome.entities.Room;

public class Heater extends Device implements Observer{
    public Heater(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }

    @Override
    public void update() {
        double temperature = getRoom().getTemperature();
        if (temperature < 20.0) enable();
        else disable();
    }
}