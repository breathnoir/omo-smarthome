package smarthome.events;

import smarthome.entities.Room;

public interface Event {
    Room getLocation();
}