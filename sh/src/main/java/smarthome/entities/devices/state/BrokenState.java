package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;
import smarthome.events.BrokenDeviceEvent;

public class BrokenState implements DeviceState{
    Device device;

    public BrokenState(Device device) {
        this.device = device;
    }

    public void enable() {
        System.out.println("device " + device.getName() + " is broken");
    }

    public void disable() {
        System.out.println("device " + device.getName() + " is broken");
    }

    public void fix() {
        device.setState(device.getOffState());
    }

    @Override
    public BrokenDeviceEvent breakDevice() {
        return null;
    }
}
