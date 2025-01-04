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
import smarthome.task.WanderAroundTheHouseTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public final class Simulation {
    private final int TICS = 20;
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

        List<Device> devices = getDevices();
        List<Inhabitant> inhabitants = getInhabitants();

        for (int tick = 0; tick < TICS; tick++) {
            System.out.println("Tick " + tick);


            // Process events in the queue without modifying it directly to avoid ConcurrentModificationException
            Queue<Event> currentEvents = new LinkedList<>(eventQueue);
            eventQueue.clear();

            // Publish unhandled events
            for (Event event : currentEvents) {
                if (!EventBus.getInstance().publishEvent(event)){
                    eventQueue.add(event);
                }
            }

            generateDeviceBreakdown(devices);

            generateCryingBabies(inhabitants);

            generatePetEvents(inhabitants);

            for (Inhabitant inhabitant : inhabitants){
                if (!inhabitant.isBusy()) inhabitant.useAvailableObject(getUsableObjects());
            }

            inhabitants.forEach(Inhabitant::progressTask);

            getRooms().forEach(room -> {
                updateRoomStats(room);
                room.notifyObservers();
            });

            devices.forEach(Device::useElectricity);

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

    public void updateRoomStats(Room room) {

        for (Sensor sensor: room.getSensors()) sensor.updateStat();
        System.out.printf("Room Stats Updated: Temperature = %.1f, Humidity = %.1f, WindSpeed = %.1f, Lightning = %.1f for room %s%n",
                room.getTemperature(), room.getHumidity(), room.getWindSpeed(), room.getLightning(), room.getName());
    }

    public void registerChains(){
        Inhabitant chain = ChainBuilder.buildChain(getInhabitants());
        EventBus.getInstance().registerChain(BrokenDeviceEvent.class, chain);
        EventBus.getInstance().registerChain(DistressedPetEvent.class, chain);
        EventBus.getInstance().registerChain(CryingBabyEvent.class, chain);
    }

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

    public void generatePetEvents(List<Inhabitant> inhabitants) {
        for (Inhabitant inhabitant : inhabitants) {
            if (inhabitant instanceof Animal animal) {
                if (!animal.isDistressed()) {
                    if (random.nextDouble() < 0.1) {
                        Event newEvent = animal.seekAttention();
                        eventQueue.add(newEvent);
                        EventBus.getInstance().publishEvent(newEvent);
                    } else {
                        List<Room> rooms = getRooms();
                        animal.assignTask(new WanderAroundTheHouseTask(animal, rooms.get(random.nextInt(rooms.size()))));
                    }
                }
            }
        }
    }
}
