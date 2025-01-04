package smarthome.events;

import smarthome.entities.Room;
import smarthome.entities.inhabitants.Animal;

public class DistressedPetEvent implements Event {
    private final Animal animal;

    public DistressedPetEvent(Animal animal) {
        this.animal = animal;
    }

    @Override
    public Room getLocation() {
        return animal.getLocation();
    }

    public Animal getAnimal() {
        return animal;
    }
}

