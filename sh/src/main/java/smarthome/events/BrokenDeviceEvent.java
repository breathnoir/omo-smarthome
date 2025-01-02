package smarthome.events;

import smarthome.entities.devices.Device;

public class BrokenDeviceEvent implements Event {
    private final Device device;

    public BrokenDeviceEvent(Device device) {
        this.device = device;
    }

    @Override
    public String getLocation() {
        return device.getRoom().getName();
    }

    public Device getDevice() {
        return device;
    }
}