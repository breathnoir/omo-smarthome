package smarthome.task;

import smarthome.entities.inhabitants.Baby;
import smarthome.entities.inhabitants.Inhabitant;

public class FeedBabyTask extends Task {
    private Baby baby;
    public FeedBabyTask(Baby baby, Inhabitant assignee) {
        super(2, assignee, baby.getLocation());
        this.baby = baby;
    }

    @Override
    public void execute() {
        baby.setCrying(false);
        System.out.println(assignee.name + " fed baby " + baby.name);
        System.out.println("Baby " + baby.name + " is not crying anymore");
    }
}
