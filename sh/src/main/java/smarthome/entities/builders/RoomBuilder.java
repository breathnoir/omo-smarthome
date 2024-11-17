package smarthome.entities.builders;

import smarthome.entities.Room;
import smarthome.entities.devices.Device;
import smarthome.entities.devices.DeviceFactory;
import smarthome.entities.sensors.Sensor;
import smarthome.entities.sensors.SensorFactory;

public class RoomBuilder {
    private Room room;

    public RoomBuilder(String roomName) {
        room = new Room(roomName);
    }

    public void addDevice(String type, String name, double electricityUsage) {
        Device device = DeviceFactory.createDevice(type, name, electricityUsage);
        room.addDevice(device);
    }

    public void addSensor(String sensorType, String name) {
        Sensor sensor = SensorFactory.createSensor(sensorType, name);
        room.addSensor(sensor);
    }

    public Room build() {
        return room;
    }
}
