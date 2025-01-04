package smarthome.task;

import smarthome.entities.inhabitants.Animal;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.reports.LoggerManager;

public class ComfortPetTask extends Task {
    private final Animal pet;
    public ComfortPetTask(Animal pet, int duration, Inhabitant assignee) {
        super(duration, assignee, pet.getLocation());
        this.pet = pet;
    }

    @Override
    public void execute() {
        pet.setDistressed(false);
//        System.out.println(assignee.name + " comforted pet " + pet.name);
        LoggerManager.eventLogger.info(assignee.name + " comforted pet " + pet.name);
    }
}
