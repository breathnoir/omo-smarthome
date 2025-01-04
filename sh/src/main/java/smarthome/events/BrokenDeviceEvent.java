package smarthome.events;

import smarthome.entities.Room;
import smarthome.entities.devices.Device;

public class BrokenDeviceEvent implements Event {
    private final Device device;

    public BrokenDeviceEvent(Device device) {
        this.device = device;
    }

    @Override
    public Room getLocation() {
        return device.getRoom();
    }

    @Override
    public Object getSource() {
        return device;
    }

    public Device getDevice() {
        return device;
    }
}