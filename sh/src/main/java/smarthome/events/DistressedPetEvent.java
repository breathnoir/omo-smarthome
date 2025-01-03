package smarthome.events;

import smarthome.entities.inhabitants.Animal;

public class DistressedPetEvent implements Event {
    private final Animal animal;

    public DistressedPetEvent(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String getLocation() {
        return animal.getLocation();
    }

    public Animal getAnimal() {
        return animal;
    }
}

