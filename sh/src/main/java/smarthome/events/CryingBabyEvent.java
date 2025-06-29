package smarthome.events;

import smarthome.entities.Room;
import smarthome.entities.inhabitants.Baby;

public class CryingBabyEvent implements Event {
    private Baby baby;

    public CryingBabyEvent(Baby baby) {
        this.baby = baby;
    }

    @Override
    public Room getLocation() {
        return baby.getLocation();
    }

    @Override
    public Object getSource() {
        return baby;
    }

    public Baby getBaby() {
        return baby;
    }
}
