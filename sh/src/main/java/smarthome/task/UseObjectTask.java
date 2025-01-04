package smarthome.task;

import smarthome.entities.UsableObject;
import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Inhabitant;

public class UseObjectTask extends Task {
    UsableObject object;
    public UseObjectTask(Inhabitant assignee, UsableObject object) {
        super(3, assignee, object.getRoom());
        this.object = object;
    }

    @Override
    public void execute() {
        object.release();
    }

    @Override
    public boolean progress() {
        if (object instanceof Device device) {
            if (device.isBroken()) {
                execute();
                return true;
            }
        }
        remainingTicks--;
        if (remainingTicks <= 0) {
            execute();
            return true;
        }
        return false;
    }
}
