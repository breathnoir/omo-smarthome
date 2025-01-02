package smarthome;

import smarthome.config.ChainBuilder;
import smarthome.entities.Floor;
import smarthome.entities.House;
import smarthome.entities.Room;
import smarthome.entities.devices.Device;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.events.BrokenDeviceEvent;
import smarthome.events.Event;
import smarthome.events.EventBus;

import java.util.LinkedList;
import java.util.Queue;

public final class Simulation {
    private final int TICS = 10;
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

        Floor floor = (Floor) house.getFloors().get(0);
        Room room = (Room) floor.getRooms().get(0);
        Device device = (Device) room.getDevices().get(1);
        Device device2 = (Device) room.getDevices().get(0);
        Device device3 = (Device) room.getDevices().get(2);
        Inhabitant inhabitant = house.getInhabitants().get(0);
        Inhabitant inhabitant2 = house.getInhabitants().get(1);

        for (int tick = 0; tick < 10; tick++) {
            System.out.println("Tick " + tick);
            for (Event event : eventQueue) {
                EventBus.getInstance().publishEvent(event);
            }
            // Generate random events
            if (tick == 2) {
                Event newevent = device.breakDevice();
                eventQueue.add(newevent);
                EventBus.getInstance().publishEvent(newevent);
            }
            if (tick == 2) {
                Event newevent = device2.breakDevice();
                eventQueue.add(newevent);
                EventBus.getInstance().publishEvent(newevent);
            }
            if (tick == 2) {
                Event newevent = device3.breakDevice();
                eventQueue.add(newevent);
                EventBus.getInstance().publishEvent(newevent);
            }
            inhabitant.progressTask();
            inhabitant2.progressTask();

        }

        Visitor report = new ConsumptionReport();
        house.acceptVisitor(report);
    }

    public Queue<Event> getEventQueue() {
        return eventQueue;
    }
}
