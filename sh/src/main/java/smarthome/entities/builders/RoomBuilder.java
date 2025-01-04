package smarthome.entities.builders;

import smarthome.entities.Room;
import smarthome.entities.devices.Device;
import smarthome.entities.devices.DeviceFactory;
import smarthome.entities.devices.Observer;
import smarthome.entities.sensors.MotionSensor;
import smarthome.entities.sensors.Sensor;
import smarthome.entities.sensors.SensorFactory;

import java.util.stream.Collectors;

public class RoomBuilder {
    private final Room room;

    public RoomBuilder(String roomName) {
        room = new Room(roomName);
    }

    public void addDevice(String type, String name, double electricityUsage) {
        Device device = DeviceFactory.createDevice(room, type, name, electricityUsage);
        room.addDevice(device);
    }

    public void addSensor(String sensorType, String name) {
        Sensor sensor = SensorFactory.createSensor(sensorType, room, name);
        room.addSensor(sensor);
        if (sensor instanceof MotionSensor) {
            room.setMotionSensor((MotionSensor) sensor);
        }
    }

    public Room build() {
        room.setObservers(room.getDevices().stream()
                .filter(dev -> dev instanceof Observer).map(dev -> (Observer) dev)
                .collect(Collectors.toList()));
        return room;
    }
}
