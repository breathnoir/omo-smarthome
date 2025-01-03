package smarthome.entities.sensors;

import smarthome.entities.Room;

public class HumiditySensor extends Sensor{

    public HumiditySensor(Room room, String name) {
        super(room, name);
    }

    @Override
    public void updateStat() {
        room.setHumidity(20.0 + (80.0 - 20.0) * random.nextDouble());    // 20.0 to 80.0
    }
}
