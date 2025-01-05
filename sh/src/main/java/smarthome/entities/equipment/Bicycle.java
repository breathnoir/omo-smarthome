package smarthome.entities.equipment;

import smarthome.entities.UsableObject;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.reports.LoggerManager;

public class Bicycle extends Equipment implements UsableObject {
    boolean isInUse = false;
    private Inhabitant currentUser;

    public Bicycle(String name) {
        super(name);
    }
    @Override
    public boolean isFree() {
        return !isInUse;
    }

    @Override
    public void use(Inhabitant user) {
        isInUse = true;
        currentUser = user;
//        System.out.println(user.name + " is using " + getName());
        LoggerManager.activityLogger.info(user.name + " is using " + getName());
    }

    @Override
    public void release() {
//        System.out.println(currentUser.name + " has stopped using " + getName());
        LoggerManager.activityLogger.info(currentUser.name + " has stopped using " + getName());
        isInUse = false;
    }
}
