package smarthome.entities.devices.state;

import smarthome.events.BrokenDeviceEvent;

public interface DeviceState {
    void enable();
    void disable();
    void fix();
    BrokenDeviceEvent breakDevice();
}
