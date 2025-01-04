package smarthome.entities.inhabitants;

import smarthome.Simulation;
import smarthome.entities.Room;
import smarthome.entities.UsableObject;
import smarthome.events.Event;
import smarthome.task.Task;
import smarthome.task.UseObjectTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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


    public boolean handleEvent(Event event) {
        if (canHandle(event) && !isBusy()) {
            processEvent(event);
            Simulation.getInstance().getEventQueue().remove(event);
            return true;
        } else if (next != null) {
            return next.handleEvent(event);
        } else {
            System.out.println("No one could handle the event: " + event.getClass().getSimpleName() + ". Event remained in the queue.");
            return false;
        }
    }

    public void setNext(Inhabitant next) {
        this.next = next;
    }

    protected abstract boolean canHandle(Event event);
    protected abstract void processEvent(Event event);

    public void assignTask(Task task) {
        taskQueue.add(task);
    }

    public void useAvailableObject(List<UsableObject> usableObjects) {
        if (!isBusy()) {
            for (UsableObject obj : usableObjects) {
                if (obj.isFree()) {
                    assignTask(new UseObjectTask(this, obj));
                    obj.use(this);
                    return;
                }
            }
            System.out.println(name + " is waiting as no UsableObjects are available.");
        }
    }

    public void progressTask() {
        if (!taskQueue.isEmpty()) {
            Task task = taskQueue.peek();
            if (task.progress()) {
                taskQueue.poll();
            }
        }
    }

    public void moveTo(Room nextRoom) {
        if (room != null) {
            room.removeInhabitants(this);
        }
        room = nextRoom;
        if (nextRoom != null) {
            nextRoom.addInhabitants(this);
            System.out.println(name + " moved to " + nextRoom.getName());
        }
    }

    public Room getLocation() {
        return room;
    }

    public boolean isBusy(){
        return !taskQueue.isEmpty();
    }
}
