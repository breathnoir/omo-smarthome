package smarthome.events;

import smarthome.entities.inhabitants.Inhabitant;
import smarthome.reports.LoggerManager;

import java.util.HashMap;
import java.util.Map;

/**
 * The EventBus class provides a centralized event-handling mechanism for the system. It allows
 * the registration of event handler chains for specific event types and facilitates the publishing
 * and processing of events.
 */
public class EventBus {
    private static EventBus instance;

    /**
     * @return The singleton instance of the EventBus.
     */
    public static synchronized EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    /**
     * A map that associates specific event types with their respective handler chains. Each entry in the
     * map corresponds to an event type, represented by a Class object that extends the {@link Event},
     * and an {@link Inhabitant} which serves as the beginning of the chain of responsibility for handling
     * events of that type.
     */
    private final Map<Class<? extends Event>, Inhabitant> handlerChains = new HashMap<>();

    /**
     * Registers a chain of responsibility for processing a specific type of event.
     * The provided chain will handle events of the specified type when they are published.
     *
     * @param eventType The class type of the event to register. Must extend {@link Event}.
     * @param chain The starting chain of responsibility, represented by an {@link Inhabitant}, which will handle events of the specified type.
     */
    public void registerChain(Class<? extends Event> eventType, Inhabitant chain) {
        handlerChains.put(eventType, chain);
    }

    /**
     * Publishes an event to the registered handler chain for its type.
     * @param event The event to be published. The event must be an instance of a class that
     *              implements the {@link Event} interface and contains information about its
     *              source and location.
     * @return true if the event was successfully handled by a registered handler chain;
     *         false if no handler chain exists for the event's type or if the event could not be handled.
     */
    public boolean publishEvent(Event event) {
        Inhabitant chain = handlerChains.get(event.getClass());
        if (chain != null) {
            return chain.handleEvent(event); // pass the event to the chain
        } else {
            System.err.println("No handlers available for event type: " + event.getClass().getSimpleName());
            LoggerManager.eventLogger.info("No handlers available for event type: " + event.getClass().getSimpleName());
            return false;
        }
    }
}
