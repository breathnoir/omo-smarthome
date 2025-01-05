package smarthome.entities.devices;

/**
 * The Observer interface represents an entity that can observe changes in another object and react to those changes.
 * It is generally used in the Observer design pattern to achieve a form of event-driven communication between objects.
 * Classes implementing this interface need to define the specific behavior to be executed in response to updates.
 */
public interface Observer {
    void update();
}
