package smarthome.task;

import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Inhabitant;

public class FindManualTask extends Task {
    private final Device device;

    public FindManualTask(Device device, Inhabitant inhabitant) {
        super(1, inhabitant);
        this.device = device;
    }

    @Override
    public void execute() {
        System.out.println(assignee.name + " found manual for " + device.getName());
    }
}
