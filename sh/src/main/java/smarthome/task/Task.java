package smarthome.task;

import smarthome.entities.Room;
import smarthome.entities.inhabitants.Inhabitant;

/**
 * Represents an abstract task that can be assigned to an inhabitant
 * and takes place in a specific location. Each task has a duration,
 * defined as the number of remaining ticks before its execution is triggered.
 */
abstract public class Task {
    int remainingTicks;
    protected Inhabitant assignee;
    Room location;

    public Task(int duration, Inhabitant assignee, Room location) {
        this.remainingTicks = duration;
        this.assignee = assignee;
        this.location = location;
    }

    /**
     * Updates the progress of the task by decrementing the remaining execution time.
     * If the task's remaining time reaches zero or is less than zero after the update,
     * the task's execution is triggered.
     *
     * @return true if the task is completed and has been executed, false otherwise.
     */
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

    /**
     * Executes the specific behavior or action associated with a particular task.
     * This method is invoked when the task is determined to be complete during
     * its progression or based on certain conditions in extending classes.
     */
    public abstract void execute();
}
