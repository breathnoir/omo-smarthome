package smarthome.events;

import smarthome.entities.Room;

/**
 * Represents a generic event that can occur in the system. This interface should be implemented
 * by all specific event types within the system. It provides methods to retrieve information
 * about the event's location and its source.
 */
public interface Event {
    /**
     * Retrieves the location associated with the event. The location is represented as a Room
     * object, which identifies the specific area where the event occurred or is associated with.
     *
     * @return The Room object representing the location of the event.
     */
    Room getLocation();
    /**
     * Retrieves the source of the event that triggered this instance. The source is an object
     * directly associated with the event, such as an entity or device that caused the event.
     *
     * @return The object that caused or is the origin of the event.
     */
    Object getSource();
}