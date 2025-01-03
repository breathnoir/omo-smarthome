package smarthome.entities.devices;

import smarthome.entities.Room;
import smarthome.entities.UsableObject;
import smarthome.entities.inhabitants.Inhabitant;

public class PC extends Device implements UsableObject {
    boolean isInUse = false;
    private Inhabitant currentUser;

    public PC(Room room, String name, double electricityUsage) {
        super(room, name, electricityUsage);
    }

    @Override
    public boolean isFree() {
        return !isInUse;
    }

    @Override
    public void use(Inhabitant user) {
        isInUse = true;
        currentUser = user;
        System.out.println(user.name + " is using " + getName());
        enable();
    }

    @Override
    public void release() {
        System.out.println(currentUser.name + " has stopped using " + getName());
        isInUse = false;
        disable();
    }
}
