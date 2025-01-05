package smarthome.task;

import smarthome.entities.inhabitants.Baby;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.reports.LoggerManager;

public class ComfortBabyTask extends Task {
    private Baby baby;
    public ComfortBabyTask(Baby baby, Inhabitant assignee) {
        super(2, assignee, baby.getLocation());
        this.baby = baby;
    }

    @Override
    public void execute() {
//        System.out.println(assignee.name + " comforted baby " + baby.name);
        LoggerManager.eventLogger.info(assignee.name + " comforted baby " + baby.name);
    }
}
