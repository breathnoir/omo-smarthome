package smarthome.entities.inhabitants;

import smarthome.events.BrokenDeviceEvent;
import smarthome.events.CryingBabyEvent;
import smarthome.events.DistressedPetEvent;
import smarthome.events.Event;
import smarthome.task.*;

public class Adult extends Inhabitant {

    public Adult(String name, int age) {
        super(name, age);
    }

    @Override
    protected boolean canHandle(Event event) {
        return event instanceof BrokenDeviceEvent || event instanceof DistressedPetEvent || event instanceof CryingBabyEvent;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof BrokenDeviceEvent brokenDeviceEvent) {
            System.out.println(name + " is attending to fix device: " + brokenDeviceEvent.getDevice().getName());
            Task newTask = new FindManualTask(brokenDeviceEvent.getDevice(), this);
            assignTask(newTask);
            moveTo(newTask.getLocation());
            assignTask(new FixDeviceTask(brokenDeviceEvent.getDevice(), this));

        } else if (event instanceof DistressedPetEvent distressedPetEvent) {
            System.out.println(name + " is attending to check " + distressedPetEvent.getAnimal().name);
            Task newTask = new ComfortPetTask(distressedPetEvent.getAnimal(), 1,this);
            assignTask(newTask);
            moveTo(newTask.getLocation());
        } else if (event instanceof CryingBabyEvent cryingBabyEvent) {
            System.out.println(name + " is attending to help " + cryingBabyEvent.getBaby().name);
            Task newTask = new ComfortBabyTask(cryingBabyEvent.getBaby(), this);
            assignTask(newTask);
            moveTo(newTask.getLocation());
            assignTask(new FeedBabyTask(cryingBabyEvent.getBaby(), this));
        }
    }

}