package smarthome.entities.inhabitants;

import smarthome.entities.UsableObject;
import smarthome.events.DistressedPetEvent;
import smarthome.events.Event;

import java.util.List;

public class Animal extends Inhabitant {

    private String species;
    private boolean isDistressed = false;

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

    public boolean isDistressed() {
        return isDistressed;
    }

    public void setDistressed(boolean distressed) {
        isDistressed = distressed;
    }

    @Override
    public void useAvailableObject(List<UsableObject> usableObjects){}
}