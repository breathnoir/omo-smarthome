package smarthome.task;

import smarthome.entities.Room;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.reports.LoggerManager;

public class WanderAroundTheHouseTask extends Task {
    public WanderAroundTheHouseTask(Inhabitant assignee, Room location) {
        super(1, assignee, location);
    }

    @Override
    public void execute() {
        assignee.moveTo( location);
//        System.out.println(assignee.name + " wandered to " + location.getName());
        LoggerManager.eventLogger.info(assignee.name + " wandered to " + location.getName());
    }
}
