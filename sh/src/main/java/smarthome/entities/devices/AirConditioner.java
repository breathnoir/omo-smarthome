package smarthome.entities.devices;

import smarthome.entities.Room;

public class AirConditioner extends Device implements Observer{
    public AirConditioner(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }

    @Override
    public void update() {
        double temperature = getRoom().getTemperature();
        if (temperature > 25.0) enable();
        else disable();
    }
}
