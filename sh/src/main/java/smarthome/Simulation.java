package smarthome;

import smarthome.config.ChainBuilder;
import smarthome.entities.House;
import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Animal;
import smarthome.entities.inhabitants.Baby;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.events.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public final class Simulation {
    private final int TICS = 100;
    private static House house;
    private Queue<Event> eventQueue = new LinkedList<>();
    private static Simulation instance;
    Random random = new Random();

    private Simulation() {}

    public static synchronized Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    public void run() {
        if (house == null) {
            System.out.println("House is null!");
            return;
        }

        registerChains();

        for (int tick = 0; tick < TICS; tick++) {
            System.out.println("Tick " + tick);

            // Publish unhandled events
            for (Event event : eventQueue) {
                EventBus.getInstance().publishEvent(event);
            }

            generateDeviceBreakdown();

            generateCryingBabies();

            generatePetEvents();

            getInhabitants().forEach(Inhabitant::progressTask);

        }

        Visitor report = new ConsumptionReport();
        house.acceptVisitor(report);
    }

    public void setHouse(House house) {
        Simulation.house = house;
    }

    public Queue<Event> getEventQueue() {
        return eventQueue;
    }

    public List<Device> getDevices(){
        return house.getAllFloors().stream()
                .flatMap(floor -> floor.getAllRooms().stream()
                        .flatMap(room -> room.getAllDevices().stream())).toList();
    }

    public List<Inhabitant> getInhabitants() {
        return house.getInhabitants();
    }

    public void registerChains(){
        Inhabitant chain = ChainBuilder.buildChain(getInhabitants());
        EventBus.getInstance().registerChain(BrokenDeviceEvent.class, chain);
        EventBus.getInstance().registerChain(DistressedPetEvent.class, chain);
        EventBus.getInstance().registerChain(CryingBabyEvent.class, chain);
    }

    public void generateDeviceBreakdown(){
        List<Device> devices = getDevices();
        for (Device device : devices) {
            if (random.nextDouble() < 0.1) {
                Event newEvent = device.breakDevice();
                if (newEvent != null) {
                    eventQueue.add(newEvent);
                    EventBus.getInstance().publishEvent(newEvent);
                }
            }
        }
    }

    public void generateCryingBabies() {
        List<Inhabitant> inhabitants = getInhabitants();
        for (Inhabitant baby: inhabitants) {
            if (baby instanceof Baby) {
                if (!((Baby) baby).isCrying()) {
                    if (random.nextDouble() < 0.1) {
                        Event newEvent = ((Baby) baby).cry();
                        eventQueue.add(newEvent);
                        EventBus.getInstance().publishEvent(newEvent);
                    }
                }
            }
        }
    }

    public void generatePetEvents() {
        List<Inhabitant> inhabitants = getInhabitants();
        for (Inhabitant animal : inhabitants) {
            if (animal instanceof Animal) {
                if (!((Animal) animal).isDistressed()) {
                    if (random.nextDouble() < 0.1) {
                        Event newEvent = ((Animal) animal).seekAttention();
                        eventQueue.add(newEvent);
                        EventBus.getInstance().publishEvent(newEvent);
                    }
                }
            }
        }
    }
}
