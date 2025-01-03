package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;
import smarthome.events.BrokenDeviceEvent;

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

    @Override
    public BrokenDeviceEvent breakDevice() {
        device.setState(device.getBrokenState());
        System.out.println(device.getName() + " is broken.");
        BrokenDeviceEvent event = new BrokenDeviceEvent(device);
        return event;
    }
}
