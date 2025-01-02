package smarthome.entities.inhabitants;

import smarthome.events.BrokenDeviceEvent;
import smarthome.events.DistressedPetEvent;
import smarthome.events.Event;
import smarthome.task.FindManualTask;
import smarthome.task.FixDeviceTask;

public class Adult extends Inhabitant {

    public Adult(String name, int age) {
        super(name, age);
    }

    @Override
    protected boolean canHandle(Event event) {
        return event instanceof BrokenDeviceEvent || event instanceof DistressedPetEvent;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof BrokenDeviceEvent brokenDeviceEvent) {
//            System.out.println(name + " is attending to fix device: " + brokenDeviceEvent.getDevice().getName());
            assignTask(new FindManualTask(brokenDeviceEvent.getDevice(), this));
            assignTask(new FixDeviceTask(brokenDeviceEvent.getDevice(), this));

        } else if (event instanceof DistressedPetEvent distressedPetEvent) {
            System.out.println(name + " is attending to check " + distressedPetEvent.getAnimal().name);
        }
    }

}