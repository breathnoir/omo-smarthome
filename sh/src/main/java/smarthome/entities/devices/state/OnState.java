package smarthome.entities.devices.state;

import smarthome.entities.UsableObject;
import smarthome.entities.devices.Device;
import smarthome.events.BrokenDeviceEvent;
import smarthome.reports.LoggerManager;

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
//        System.out.println("device " + device.getName() + " is off");
        if (!(device instanceof UsableObject)) {
            LoggerManager.sensorLogger.info("Device " + device.getName() + " in " + device.getRoom() + " is off");
        }
    }

    public void fix() {

    }

    @Override
    public BrokenDeviceEvent breakDevice() {
        device.setState(device.getBrokenState());
        device.setUsingElectricity(false);
        BrokenDeviceEvent event = new BrokenDeviceEvent(device);
//        System.out.println(device.getName() + " is broken. ");
        LoggerManager.eventLogger.info("Device " + device.getName() + " in " + device.getRoom() + " is broken");

        if (!(device instanceof UsableObject)) {
            LoggerManager.sensorLogger.info("Device " + device.getName() + " in " + device.getRoom() + " is broken");
        }

        return event;
    }
}
