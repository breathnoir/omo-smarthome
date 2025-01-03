package smarthome.entities.inhabitants;


import smarthome.entities.UsableObject;
import smarthome.events.CryingBabyEvent;
import smarthome.events.Event;

import java.util.List;

public class Baby extends Inhabitant {

    private boolean isCrying = false;

    public Baby(String name, int age) {
        super(name, age);
    }

    public CryingBabyEvent cry(){
        System.out.println("Baby " + name + " is crying");
        isCrying = true;
        return new CryingBabyEvent(this);
    }

    @Override
    protected boolean canHandle(Event event) {
        return false;
    }

    @Override
    protected void processEvent(Event event) {

    }

    public boolean isCrying() {
        return isCrying;
    }

    public void setCrying(boolean crying) {
        isCrying = crying;
    }

    @Override
    public void useAvailableObject(List<UsableObject> usableObjects){}
}