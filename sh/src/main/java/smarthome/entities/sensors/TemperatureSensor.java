package smarthome.entities.sensors;

import smarthome.entities.Room;

public class TemperatureSensor extends Sensor {


    public TemperatureSensor(Room room, String name) {
        super(room, name);
    }

    @Override
    public void updateStat() {
        room.setTemperature(15.0 + (35.0 - 15.0) * random.nextDouble()); // 15.0 to 35.0
    }
}
