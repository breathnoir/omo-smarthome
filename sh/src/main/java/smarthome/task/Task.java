package smarthome.task;

import smarthome.entities.Room;
import smarthome.entities.inhabitants.Inhabitant;

abstract public class Task {
    int remainingTicks;
    protected Inhabitant assignee;
    Room location;

    public Task(int duration, Inhabitant assignee, Room location) {
        this.remainingTicks = duration;
        this.assignee = assignee;
        this.location = location;
    }

    public boolean progress() {
        remainingTicks--;
        if (remainingTicks <= 0) {
            execute();
            return true;
        }
        return false;
    }

    public Room getLocation() {
        return location;
    }

    public abstract void execute();
}
