package smarthome.entities.sensors;

import smarthome.entities.Room;

public class WindSensor extends Sensor {

    public WindSensor(Room room, String name) {
        super(room, name);
    }

    @Override
    public void updateStat() {
        room.setWindSpeed(0.0 + (50.0 - 0.0) * random.nextDouble());     // 0.0 to 50.0
    }
}
