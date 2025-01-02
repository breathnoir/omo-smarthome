package smarthome.entities.inhabitants;

import smarthome.entities.Floor;
import smarthome.entities.Room;
import smarthome.events.Event;
import smarthome.task.Task;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Inhabitant {
    public String name;
    private boolean isHome;
    private Room room;
    private Floor floor;
    private int age;
    protected Inhabitant next;
    protected Task currentTask;
    protected final Queue<Task> taskQueue = new LinkedList<>();

    public Inhabitant(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public void handleEvent(Event event) {
        if (canHandle(event) && !isBusy()) {
            processEvent(event);
        } else if (next != null) {
            next.handleEvent(event);
        } else {
            System.out.println("No one could handle the event: " + event.getClass().getSimpleName());

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

    public void progressTask() {
        if (!taskQueue.isEmpty()) {
            Task task = taskQueue.peek();
            if (task.progress()) {
                taskQueue.poll();
            }// Remove completed task
        }
    }

    public String getLocation() {
        return null;
    }

    public boolean isBusy(){
        return !taskQueue.isEmpty();
    }
}
