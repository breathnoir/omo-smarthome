package smarthome.entities.inhabitants;

import smarthome.entities.Floor;
import smarthome.entities.Room;
import smarthome.events.Event;

public abstract class Inhabitant {
    public String name;
    private boolean isHome;
    private Room room;
    private Floor floor;
    private int age;
    protected Inhabitant next;

    public Inhabitant(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void handleEvent(Event event) {
        if (canHandle(event)) {
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

    public String getLocation() {
        return null;
    }
}
