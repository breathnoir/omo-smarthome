package smarthome;

import smarthome.config.ChainBuilder;
import smarthome.entities.House;
import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Animal;
import smarthome.entities.inhabitants.Baby;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.events.BrokenDeviceEvent;
import smarthome.events.DistressedPetEvent;
import smarthome.events.Event;
import smarthome.events.EventBus;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public final class Simulation {
    private final int TICS = 100;
    private static House house;
    private Queue<Event> eventQueue = new LinkedList<>();
    private static Simulation instance;

    private Simulation() {}

    public static synchronized Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    public void setHouse(House house) {
        Simulation.house = house;
    }

    public void run() {
        if (house == null) {
            System.out.println("House is null!");
            return;
        }

        Inhabitant chain = ChainBuilder.buildChain(house.getInhabitants());
        EventBus.getInstance().registerChain(BrokenDeviceEvent.class, chain);
        EventBus.getInstance().registerChain(DistressedPetEvent.class, chain);

        List<Inhabitant> inhabitants = house.getInhabitants();
        List<Device> devices = house.getAllFloors().stream()
                .flatMap(floor -> floor.getAllRooms().stream()
                        .flatMap(room -> room.getAllDevices().stream())).toList();

        for (int tick = 0; tick < TICS; tick++) {
            System.out.println("Tick " + tick);

            // Publish unhandled events
            for (Event event : eventQueue) {
                EventBus.getInstance().publishEvent(event);
            }
            Random random = new Random();

            //TODO: add check if device is already broken

            // Generate device breakdown
            for (Device device : devices) {
                if (random.nextDouble() < 0.1) {
                    Event newEvent = device.breakDevice();
                    eventQueue.add(newEvent);
                    EventBus.getInstance().publishEvent(newEvent);
                }
            }

            //TODO: add check if pet is already distressed

            // Generate pet events
            for (Inhabitant animal : inhabitants) {
                if (animal instanceof Animal) {
                    if (random.nextDouble() < 0.1) {
                        Event newEvent = ((Animal) animal).seekAttention();
                        eventQueue.add(newEvent);
                        EventBus.getInstance().publishEvent(newEvent);
                    }
                }
            }

            //TODO: add check if baby is already crying

            // Generate baby events
            for (Inhabitant baby: inhabitants) {
                if (baby instanceof Baby) {
                    if (random.nextDouble() < 0.1) {
                        Event newEvent = ((Baby) baby).cry();
                        eventQueue.add(newEvent);
                        EventBus.getInstance().publishEvent(newEvent);
                    }
                }
            }

            inhabitants.forEach(Inhabitant::progressTask);

        }

        Visitor report = new ConsumptionReport();
        house.acceptVisitor(report);
    }

    public Queue<Event> getEventQueue() {
        return eventQueue;
    }
}
