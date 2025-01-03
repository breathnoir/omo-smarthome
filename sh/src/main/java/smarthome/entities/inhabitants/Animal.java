package smarthome.entities.inhabitants;

import smarthome.events.DistressedPetEvent;
import smarthome.events.Event;

public class Animal extends Inhabitant {

    private String species;

    public Animal(String name, int age, String species) {
        super(name, age);
        this.species = species;
    }

    public DistressedPetEvent seekAttention() {
        System.out.println(species + " " + name + " is seeking attention.");
        return new DistressedPetEvent(this);
    }

    @Override
    protected boolean canHandle(Event event) {
        return false;
    }

    @Override
    protected void processEvent(Event event) {

    }
}