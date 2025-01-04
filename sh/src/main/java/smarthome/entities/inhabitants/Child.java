package smarthome.entities.inhabitants;

import smarthome.events.DistressedPetEvent;
import smarthome.events.Event;
import smarthome.task.ComfortPetTask;
import smarthome.task.Task;

public class Child extends Inhabitant {

    public Child(String name, int age) {
        super(name, age);
    }

    @Override
    protected boolean canHandle(Event event) {
        return event instanceof DistressedPetEvent;
    }

    @Override
    protected void processEvent(Event event) {
        if (event instanceof DistressedPetEvent distressedPetEvent) {
            System.out.println(name + " is attending to check " + distressedPetEvent.getAnimal().name);
            Task newTask = new ComfortPetTask(distressedPetEvent.getAnimal(), 2,this);
            assignTask(newTask);
            moveTo(newTask.getLocation());
        }
    }
}