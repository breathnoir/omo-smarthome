package smarthome.entities.inhabitants;

import smarthome.Simulation;
import smarthome.entities.Room;
import smarthome.entities.UsableObject;
import smarthome.events.Event;
import smarthome.reports.LoggerManager;
import smarthome.task.Task;
import smarthome.task.UseObjectTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Inhabitant class represents an abstract entity capable of performing tasks, interacting
 * with events, and navigating between different rooms. It provides a basic structure for handling
 * tasks and events, and can be extended to define specific behaviors for different types of
 * inhabitants.
 * Subclasses must implement the abstract methods {@code canHandle(Event)} and {@code processEvent(Event)}
 * to specify the conditions under which an event can be handled and the manner in which it is processed.
 */
public abstract class Inhabitant {
    public String name;
    private boolean isHome;
    private Room room;
    private int age;
    protected Inhabitant next;
    protected Task currentTask;
    protected final Queue<Task> taskQueue = new LinkedList<>();

    public Inhabitant(String name, int age) {
        this.name = name;
        this.age = age;
    }


    /**
     * Handles the provided event by determining whether it can be processed by the current inhabitant.
     *
     * @param event The event to be handled. It contains information about its source and location.
     * @return true if the event was successfully handled by this inhabitant or a subsequent one in the chain;
     *         false if no inhabitant in the chain was able to handle the event.
     */
    public boolean handleEvent(Event event) {
        if (canHandle(event) && !isBusy()) {
            processEvent(event);
            Simulation.getInstance().getEventQueue().remove(event);
            return true;
        } else if (next != null) {
            return next.handleEvent(event);
        } else {
//            System.out.println("No one could handle the event: " + event.getClass().getSimpleName() + ". Event remained in the queue.");
            LoggerManager.eventLogger.info("No one could handle the event: " + event.getClass().getSimpleName()
                    + " caused by " + event.getSource().toString() + ". Event remained in the queue.");
            return false;
        }
    }

    public void setNext(Inhabitant next) {
        this.next = next;
    }

    /**
     * Determines if the current inhabitant is capable of handling the specified event.
     *
     * @param event The event to check. Contains details such as its source and location.
     * @return true if the inhabitant can handle the event; false otherwise.
     */
    protected abstract boolean canHandle(Event event);
    /**
     * Processes the specified event, allowing the inhabitant to take actions
     * or assign tasks based on the event's type and details.
     *
     * @param event The event to be processed. Provides information such as its source and location.
     */
    protected abstract void processEvent(Event event);

    /**
     * Assigns the specified task to the task queue of the inhabitant.
     *
     * @param task The task to be assigned. This task will be added to the
     *             inhabitant's task queue for execution or tracking.
     */
    public void assignTask(Task task) {
        taskQueue.add(task);
    }

    /**
     * Attempts to use an available {@link UsableObject} from the provided list of usable objects.
     * If the current inhabitant is busy, no action is taken. The method iterates through the list
     * of usable objects to find a free object, assigns a task to use it, moves to the object's location,
     * and uses the object.
     *
     * @param usableObjects A list of {@link UsableObject} instances that can potentially be used by the inhabitant.
     *                      The method will iterate over these objects to check their availability.
     */
    public void useAvailableObject(List<UsableObject> usableObjects) {
        if (!isBusy()) {
            for (UsableObject obj : usableObjects) {
                if (obj.isFree()) {
                    assignTask(new UseObjectTask(this, obj));
                    moveTo(obj.getRoom());
                    obj.use(this);
                    return;
                }
            }
//            System.out.println(name + " is waiting as no UsableObjects are available.");
            LoggerManager.activityLogger.info(name + " is waiting as no UsableObjects are available.");
        }
    }

    /**
     * Attempts to make progress on the task at the front of the task queue.
     * If the queue is empty, no action is performed.
     *
     * If a task exists at the front of the queue, its progress method is called.
     * If the task is completed (indicated by the progress method returning true),
     * it is removed from the queue.
     */
    public void progressTask() {
        if (!taskQueue.isEmpty()) {
            Task task = taskQueue.peek();
            if (task.progress()) {
                taskQueue.poll();
            }
        }
    }

    /**
     * Moves the inhabitant to a specified room. Updates the current room's state by removing the inhabitant
     * and adds the inhabitant to the specified next room if it is not null.
     *
     * @param nextRoom The room to which the inhabitant will be moved. If null, the inhabitant will be marked as not home.
     */
    public void moveTo(Room nextRoom) {
        if (room != null) {
            room.removeInhabitants(this);
        }
        room = nextRoom;
        if (nextRoom != null) {
            nextRoom.addInhabitants(this);
//            System.out.println(name + " moved to " + nextRoom.getName());
            LoggerManager.activityLogger.info(name + " moved to " + nextRoom.getName());
            LoggerManager.sensorLogger.info(name + " moved to " + nextRoom.getName());

            isHome = true;
        } else {
//            System.out.println(name + " is not home.");
            LoggerManager.activityLogger.info(name + " is not home.");
            isHome = false;
        }
    }

    public Room getLocation() {
        return room;
    }

    public boolean isBusy(){
        return !taskQueue.isEmpty();
    }

    @Override
    public String toString() {
        return "Inhabitant " + name + ", " + age;
    }
}
