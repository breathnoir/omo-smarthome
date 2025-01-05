package smarthome.task;

import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.reports.LoggerManager;

public class FindManualTask extends Task {
    private final Device device;

    public FindManualTask(Device device, Inhabitant inhabitant) {
        super(1, inhabitant, device.getRoom());
        this.device = device;
    }

    @Override
    public void execute() {
//        System.out.println(assignee.name + " found manual for " + device.getName());
        LoggerManager.eventLogger.info(assignee.name + " found manual for " + device.getName());
    }
}
