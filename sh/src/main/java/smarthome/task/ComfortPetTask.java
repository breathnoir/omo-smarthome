package smarthome.task;

import smarthome.entities.inhabitants.Animal;
import smarthome.entities.inhabitants.Inhabitant;

public class ComfortPetTask extends Task {
    private final Animal pet;
    public ComfortPetTask(Animal pet, int duration, Inhabitant assignee) {
        super(duration, assignee);
        this.pet = pet;
    }

    @Override
    public void execute() {
        System.out.println(assignee.name + " comforted pet " + pet.name);
    }
}
