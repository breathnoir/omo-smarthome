package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;
import smarthome.events.BrokenDeviceEvent;

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

    @Override
    public BrokenDeviceEvent breakDevice() {
        device.setState(device.getBrokenState());
        System.out.println(device.getName() + " is broken.");
        BrokenDeviceEvent event = new BrokenDeviceEvent(device);
        return event;
    }
}
