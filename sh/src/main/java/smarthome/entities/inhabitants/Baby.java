package smarthome.entities.inhabitants;


import smarthome.events.Event;

public class Baby extends Inhabitant {

    public Baby(String name, int age) {
        super(name, age);
    }

    @Override
    protected boolean canHandle(Event event) {
        return false;
    }

    @Override
    protected void processEvent(Event event) {

    }
}