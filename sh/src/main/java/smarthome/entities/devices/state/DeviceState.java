package smarthome.entities.devices.state;

import smarthome.events.BrokenDeviceEvent;

/**
 * Represents the state of a device in a smart home system.
 * Implementations of this interface define behaviors and transitions between
 * states such as "on", "off", and "broken".
 */
public interface DeviceState {
    void enable();
    void disable();
    void fix();
    /**
     * Transitions the device to a "broken" state and generates a corresponding
     * event. Depending on the current implementation of the device state,
     * this method notifies appropriate loggers and updates the device's status
     * to indicate it is no longer operational.
     *
     * @return a BrokenDeviceEvent that encapsulates information about the device
     *         that has been marked as broken.
     */
    BrokenDeviceEvent breakDevice();
}
