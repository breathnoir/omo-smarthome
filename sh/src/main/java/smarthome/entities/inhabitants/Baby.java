package smarthome.entities.inhabitants;


import smarthome.events.CryingBabyEvent;
import smarthome.events.Event;

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
}