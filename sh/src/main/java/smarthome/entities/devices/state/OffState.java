package smarthome.entities.devices.state;

import smarthome.entities.devices.Device;
import smarthome.events.BrokenDeviceEvent;
import smarthome.reports.LoggerManager;

public class OffState implements DeviceState{
    Device device;

    public OffState(Device device) {
        this.device = device;
    }
    public void enable() {
        device.setState(device.getOnState());
        System.out.println("device " + device.getName() + " is on");
        device.setUsingElectricity(true);
    }

    public void disable() {

    }

    public void fix() {

    }

    @Override
    public BrokenDeviceEvent breakDevice() {
        device.setState(device.getBrokenState());
        BrokenDeviceEvent event = new BrokenDeviceEvent(device);
//        System.out.println(device.getName() + " is broken. ");
        LoggerManager.eventLogger.info("Device " + device.getName() + " in " + device.getRoom() + " is broken");
        return event;
    }
}
