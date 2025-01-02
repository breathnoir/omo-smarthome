package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;

public class OffState implements DeviceState{
    Device device;

    public OffState(Device device) {
        this.device = device;
    }
    public void enable() {
        device.setState(device.getOnState());
        System.out.println("device " + device.getName() + " is on");
        device.useElectricity();
    }

    public void disable() {

    }

    public void fix() {

    }
}
