package smarthome;

import smarthome.config.ChainBuilder;
import smarthome.entities.House;
import smarthome.entities.Room;
import smarthome.entities.UsableObject;
import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Animal;
import smarthome.entities.inhabitants.Baby;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.entities.sensors.Sensor;
import smarthome.events.*;
import smarthome.reports.ConsumptionReport;
import smarthome.reports.LoggerManager;
import smarthome.reports.Visitor;
import smarthome.task.WanderAroundTheHouseTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Simulation represents the core class responsible for managing and executing the simulation of a house
 * environment. This class controls the lifecycle of a simulation, including initializing events,
 * managing state changes, and coordinating interactions between various components like rooms, devices,
 * and inhabitants.
 */
public final class Simulation {
    /**
     * Represents the default number of ticks per simulation cycle.
     */
    private final int TICS = 20;
    private static House house;
    /**
     * Represents a queue to manage and store pending events within the simulation.
     *
     * Events stored in this queue are instances of objects that
     * implement the {@link Event} interface, representing various actions or occurrences
     * within the simulation, such as device malfunctions, inhabitant activities, or pet-related events.
     */
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

    /**
     * Executes the simulation by processing events, managing inhabitants and devices,
     * and updating room states over a series of predefined ticks. This method orchestrates
     * the interaction among the components of the house simulation.
     */
    public void run() {
        if (house == null) {
            System.err.println("House is null!");
            return;
        }

        registerChains();

        List<Device> devices = getDevices();
        List<Inhabitant> inhabitants = getInhabitants();

        for (int tick = 0; tick < TICS; tick++) {
            printTicks(tick);

            // Process events in the queue without modifying it directly to avoid ConcurrentModificationException
            Queue<Event> currentEvents = new LinkedList<>(eventQueue);
            eventQueue.clear();

            // Publish unhandled events
            for (Event event : currentEvents) {
                if (!EventBus.getInstance().publishEvent(event)){
                    eventQueue.add(event);
                }
            }
            generateCryingBabies(inhabitants);

            generatePetEvents(inhabitants);

            generateDeviceBreakdown(devices);

            for (Inhabitant inhabitant : inhabitants){
                if (!inhabitant.isBusy()) inhabitant.useAvailableObject(getUsableObjects());
            }

            inhabitants.forEach(Inhabitant::progressTask);

            getRooms().forEach(room -> {
                updateRoomStats(room);
                room.notifyObservers();
            });

            devices.forEach(Device::useElectricity);

            printSeparators();
        }

        Visitor report = new ConsumptionReport();
        house.acceptVisitor(report);
        ConsumptionReport.getTotalUsage();
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

    public List<UsableObject> getUsableObjects() {
        List<UsableObject> allObjects = new java.util.ArrayList<>(getDevices().stream()
                .filter(device -> device instanceof UsableObject && !device.isBroken()).map(device -> (UsableObject) device).toList());
        allObjects.addAll( house.getEquipment().stream()
                .filter(eq -> eq instanceof UsableObject).map(eq -> (UsableObject) eq).toList());
        return allObjects;
    }

    public List<Room> getRooms(){
        return house.getAllFloors().stream()
                .flatMap(floor -> floor.getAllRooms().stream()).toList();
    }

    public List<Inhabitant> getInhabitants() {
        return house.getInhabitants();
    }

    /**
     * Updates the stats of all sensors in the specified room.
     * @param room the room whose sensors and statistics should be updated
     */
    public void updateRoomStats(Room room) {

        for (Sensor sensor: room.getSensors()) sensor.updateStat();
//        System.out.printf("Room Stats Updated: Temperature = %.1f, Humidity = %.1f, WindSpeed = %.1f, Lightning = %.1f for room %s%n",
//                room.getTemperature(), room.getHumidity(), room.getWindSpeed(), room.getLightning(), room.getName());
        LoggerManager.sensorLogger.info(String.format(
                "   >%s Stats Updated: Temperature = %.1f, Humidity = %.1f, WindSpeed = %.1f, Lightning = %.1f",
                room.getName(), room.getTemperature(), room.getHumidity(), room.getWindSpeed(), room.getLightning()));

    }

    /**
     * Registers event handling chains for various event types in the system.
     * A chain of {@link Inhabitant} objects is created using the inhabitants of the simulation.
     * The chain is then registered with the {@link EventBus} to handle specific event types.
     */
    public void registerChains(){
        Inhabitant chain = ChainBuilder.buildChain(getInhabitants());
        EventBus.getInstance().registerChain(BrokenDeviceEvent.class, chain);
        EventBus.getInstance().registerChain(DistressedPetEvent.class, chain);
        EventBus.getInstance().registerChain(CryingBabyEvent.class, chain);
    }

    /**
     * Simulates the random breakdown of devices in the system. If a device breaks,
     * a corresponding event is created, added to the event queue, and published to the EventBus.
     * @param devices the list of devices to check for potential breakdowns
     */
    public void generateDeviceBreakdown(List<Device> devices){
        for (Device device : devices) {
            if (random.nextDouble() < 0.03 && !device.isBroken()) {
                Event newEvent = device.breakDevice();
                if (newEvent != null) {
                    eventQueue.add(newEvent);
                    EventBus.getInstance().publishEvent(newEvent);
                }
            }
        }
    }

    /**
     * Generates crying events for baby inhabitants in the simulation.
     * This event is added to the event queue
     * and subsequently published to the EventBus for handling by other components of the system.
     */
    public void generateCryingBabies(List<Inhabitant> inhabitants) {
        for (Inhabitant inhabitant: inhabitants) {
            if (inhabitant instanceof Baby baby) {
                if (!baby.isCrying()) {
                    if (random.nextDouble() < 0.1) {
                        Event newEvent = baby.cry();
                        eventQueue.add(newEvent);
                        EventBus.getInstance().publishEvent(newEvent);
                    }
                }
            }
        }
    }

    /**
     * Generates events for pet inhabitants in the simulation. This method
     * randomly determines whether the animal will seek attention
     * or perform a wandering task.
     */
    public void generatePetEvents(List<Inhabitant> inhabitants) {
        for (Inhabitant inhabitant : inhabitants) {
            if (inhabitant instanceof Animal animal) {
                if (!animal.isDistressed()) {
                    if (random.nextDouble() < 0.1) {
                        Event newEvent = animal.seekAttention();
                        eventQueue.add(newEvent);
                        EventBus.getInstance().publishEvent(newEvent);
                    } else {
                        if (random.nextDouble() < 0.7) {
                            List<Room> rooms = getRooms();
                            animal.assignTask(new WanderAroundTheHouseTask(animal, rooms.get(random.nextInt(rooms.size()))));
                        }
                    }
                }
            }
        }
    }

    public void printTicks(int tick) {
        System.out.println("Tick " + tick);
        LoggerManager.eventLogger.info(String.format("Tick %d:", tick));
        LoggerManager.eventLogger.info("                              ");
        LoggerManager.activityLogger.info(String.format("Tick %d:", tick));
        LoggerManager.activityLogger.info("                              ");
        LoggerManager.sensorLogger.info(String.format("Tick %d:", tick));
        LoggerManager.sensorLogger.info("                              ");
    }

    public void printSeparators() {
        System.out.println("-----------------------------------");
        LoggerManager.eventLogger.info("-----------------------------------");
        LoggerManager.activityLogger.info("-----------------------------------");
        LoggerManager.sensorLogger.info("-----------------------------------");
    }
}
