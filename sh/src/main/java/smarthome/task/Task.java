package smarthome.task;

import smarthome.entities.inhabitants.Inhabitant;

abstract public class Task {
    int remainingTicks;
    protected Inhabitant assignee;

    public Task(int duration, Inhabitant assignee) {
        this.remainingTicks = duration;
        this.assignee = assignee;
    }

    public boolean progress() {
        remainingTicks--;
        if (remainingTicks <= 0) {
            execute();
            return true;
        }
        return false;
    }

    public abstract void execute();
}
