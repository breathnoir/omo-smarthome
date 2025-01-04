package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;
import smarthome.events.BrokenDeviceEvent;

public class OnState implements DeviceState{
    Device device;

    public OnState(Device device) {
        this.device = device;
    }
    public void enable() {
        device.setUsingElectricity(true);
    }

    public void disable() {
        device.setState(device.getOffState());
        device.setUsingElectricity(false);
        System.out.println("device " + device.getName() + " is off");
    }

    public void fix() {

    }

    @Override
    public BrokenDeviceEvent breakDevice() {
        device.setState(device.getBrokenState());
        device.setUsingElectricity(false);
        BrokenDeviceEvent event = new BrokenDeviceEvent(device);
        System.out.println(device.getName() + " is broken. ");
        return event;
    }
}
