package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;

public class BrokenState implements DeviceState{
    Device device;

    public BrokenState(Device device) {
        this.device = device;
    }

    public void enable() {
        System.out.println("device " + device.getName() + " is broken");
    }

    public void disable() {

    }

    public void fix() {

    }
}
