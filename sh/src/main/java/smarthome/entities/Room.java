package smarthome.entities;

import smarthome.entities.devices.Device;
import smarthome.entities.sensors.Sensor;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private List<Device> devices = new ArrayList<>();
    private List<Sensor> sensors = new ArrayList<>();

    public Room(String roomName) {
        this.name = roomName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }


}
