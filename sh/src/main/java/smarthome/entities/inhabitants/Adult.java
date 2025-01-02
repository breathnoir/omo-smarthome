package smarthome.entities.inhabitants;

import smarthome.events.BrokenDeviceEvent;
import smarthome.events.DistressedPetEvent;
import smarthome.events.Event;

public class Adult extends Inhabitant {

    public Adult(String name, int age) {
        super(name, age);
    }

    @Override
    protected boolean canHandle(Event event) {
        return event instanceof BrokenDeviceEvent || event instanceof DistressedPetEvent;
    }

    @Override
    protected void processEvent(Event event) {
        if (event instanceof BrokenDeviceEvent brokenDeviceEvent) {
            System.out.println(name + " is attending to fix device: " + brokenDeviceEvent.getDevice().getName());
        } else if (event instanceof DistressedPetEvent distressedPetEvent) {
            System.out.println(name + " is attending to check " + distressedPetEvent.getAnimal().name);
        }
    }

}