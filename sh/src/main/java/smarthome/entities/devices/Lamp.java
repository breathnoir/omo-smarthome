package smarthome.entities.devices;

import smarthome.entities.Room;

public class Lamp extends Device implements Observer{
    public Lamp(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }

    @Override
    public void update() {
        if (getRoom().isMotionDetected()) {
            if (getRoom().getLightning() < 650) enable();
            getRoom().setMotionDetected(false);
        }
        if (getRoom().getLightning() >= 650 ) disable();
    }
}
