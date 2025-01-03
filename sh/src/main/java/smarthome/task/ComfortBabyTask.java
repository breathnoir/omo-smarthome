package smarthome.task;

import smarthome.entities.inhabitants.Baby;
import smarthome.entities.inhabitants.Inhabitant;

public class ComfortBabyTask extends Task {
    private Baby baby;
    public ComfortBabyTask(Baby baby, Inhabitant assignee) {
        super(2, assignee);
        this.baby = baby;
    }

    @Override
    public void execute() {
        System.out.println(assignee.name + " comforted baby " + baby.name);
    }
}
