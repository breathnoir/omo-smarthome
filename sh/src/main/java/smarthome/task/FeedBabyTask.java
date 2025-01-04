package smarthome.task;

import smarthome.entities.inhabitants.Baby;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.reports.LoggerManager;

public class FeedBabyTask extends Task {
    private Baby baby;
    public FeedBabyTask(Baby baby, Inhabitant assignee) {
        super(2, assignee, baby.getLocation());
        this.baby = baby;
    }

    @Override
    public void execute() {
        baby.setCrying(false);
//        System.out.println(assignee.name + " fed baby " + baby.name);
//        System.out.println("Baby " + baby.name + " is not crying anymore");
        LoggerManager.eventLogger.info(assignee.name + " fed baby " + baby.name);
        LoggerManager.eventLogger.info("Baby " + baby.name + " is not crying anymore");
    }
}
