package smarthome.entities.equipment;

import smarthome.entities.UsableObject;
import smarthome.entities.inhabitants.Inhabitant;

public class Skis extends Equipment implements UsableObject {
    boolean isInUse = false;
    private Inhabitant currentUser;

    public Skis(String name) {
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
        System.out.println(user.name + " is using " + getName());
    }

    @Override
    public void release() {
        System.out.println(currentUser.name + " has stopped using " + getName());
        isInUse = false;
    }
}
