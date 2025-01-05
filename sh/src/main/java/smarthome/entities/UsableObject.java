package smarthome.entities;

import smarthome.entities.inhabitants.Inhabitant;

/**
 * Represents an object that can be used by an inhabitant and released afterward.
 * Classes implementing this interface should define the behavior of usage,
 * checking availability, and association with a specific room.
 */
public interface UsableObject {
    boolean isFree();
    void use(Inhabitant user);
    void release();
    Room getRoom();
}
