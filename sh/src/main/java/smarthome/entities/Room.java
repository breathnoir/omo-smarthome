package smarthome.entities;

import smarthome.HouseComponent;
import smarthome.Visitor;
import smarthome.entities.devices.Device;
import smarthome.entities.sensors.Sensor;
import smarthome.iterators.HouseComponentIterator;
import smarthome.iterators.RoomIterator;

import java.util.ArrayList;
import java.util.List;

public class Room implements HouseComponent {
    private String name;
    private List<HouseComponent> devices = new ArrayList<>();
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


    @Override
    public void accept(Visitor visitor) {
        visitor.visitRoom(this);
        HouseComponentIterator iterator = iterator();
        while (iterator.hasNext()) {
            HouseComponent device = iterator.next();
            device.accept(visitor);
        }
    }

    @Override
    public HouseComponentIterator iterator() {
        return new RoomIterator(devices);
    }
}
