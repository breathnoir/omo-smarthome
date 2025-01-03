package smarthome.entities.sensors;

import smarthome.entities.Room;

public class LightSensor extends Sensor {

    public LightSensor(Room room, String name) {
        super(room, name);
    }

    @Override
    public void updateStat() {
        room.setLightning(400.0 + (800.0 - 400.0) * random.nextDouble()); // 400.0 to 800.0
    }
}
