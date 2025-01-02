package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;

public class OnState implements DeviceState{
    Device device;

    public OnState(Device device) {
        this.device = device;
    }
    public void enable() {
        device.useElectricity();
    }

    public void disable() {
        device.setState(device.getOffState());
        System.out.println("device " + device.getName() + " is off");
    }

    public void fix() {

    }
}
