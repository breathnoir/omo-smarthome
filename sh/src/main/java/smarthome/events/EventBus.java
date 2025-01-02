package smarthome.events;

import smarthome.Simulation;
import smarthome.entities.inhabitants.Inhabitant;

import java.util.HashMap;
import java.util.Map;

public class EventBus {
    private static EventBus instance;

    public static synchronized EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    private final Map<Class<? extends Event>, Inhabitant> handlerChains = new HashMap<>();

    public void registerChain(Class<? extends Event> eventType, Inhabitant chain) {
        handlerChains.put(eventType, chain);
    }

    public void publishEvent(Event event) {
        Inhabitant chain = handlerChains.get(event.getClass());
        if (chain != null) {
            chain.handleEvent(event); // pass the event to the chain
        } else {
            System.out.println("No handlers available for event type: " + event.getClass().getSimpleName());
        }
    }
}
