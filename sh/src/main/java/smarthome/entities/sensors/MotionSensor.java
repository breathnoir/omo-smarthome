package smarthome.entities.sensors;

import smarthome.entities.Room;

public class MotionSensor extends Sensor {

    public MotionSensor(Room room, String name) {
        super(room, name);
    }

    @Override
    public void updateStat() {
        room.setMotionDetected(isMotionDetected());
    }

    public boolean isMotionDetected() {
        return room.getInhabitantsCount() > 0;
    }
}
