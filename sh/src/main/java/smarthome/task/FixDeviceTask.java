package smarthome.task;

import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Inhabitant;

public class FixDeviceTask extends Task {
    private final Device device;

    public FixDeviceTask(Device device, Inhabitant inhabitant) {
        super(2, inhabitant);
        this.device = device;
    }

    @Override
    public void execute() {
        System.out.println(assignee.name + " fixed device: " + device.getName());
        device.fix();
    }
}
