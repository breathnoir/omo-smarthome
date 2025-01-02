package smarthome;

import com.fasterxml.jackson.databind.ObjectMapper;
import smarthome.config.*;
import smarthome.entities.Floor;
import smarthome.entities.House;
import smarthome.entities.Room;
import smarthome.entities.builders.FloorBuilder;
import smarthome.entities.builders.HouseBuilder;
import smarthome.entities.builders.RoomBuilder;
import smarthome.entities.devices.Device;
import smarthome.entities.equipment.Equipment;
import smarthome.entities.equipment.EquipmentFactory;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.entities.inhabitants.InhabitantFactory;
import smarthome.events.BrokenDeviceEvent;
import smarthome.events.Event;
import smarthome.events.EventBus;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

public class Start {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        String configFile = "houseconfig.json";

        try {
            InputStream inputStream = Start.class.getClassLoader().getResourceAsStream(configFile);

            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: " + configFile);
            }

            HouseConfig houseConfig = mapper.readValue(inputStream, HouseConfig.class);
            House house = buildHouse(houseConfig);

            Inhabitant chain = ChainBuilder.buildChain(house.getInhabitants());
            EventBus.getInstance().registerChain(BrokenDeviceEvent.class, chain);
            Queue<Event> eventQueue = new LinkedList<>();

            Floor floor = (Floor) house.getFloors().get(0);
            Room room = (Room) floor.getRooms().get(0);
            Device device = (Device) room.getDevices().get(1);
            Device device2 = (Device) room.getDevices().get(0);
            Device device3 = (Device) room.getDevices().get(2);
            Inhabitant inhabitant = house.getInhabitants().get(0);
            Inhabitant inhabitant2 = house.getInhabitants().get(1);

            for (int tick = 0; tick < 10; tick++) {
                System.out.println("Tick " + tick);

                // Generate random events
                if (tick == 1) {
                    eventQueue.add(device.breakDevice());
                }
                if (tick == 2) {
                    eventQueue.add(device2.breakDevice());
                }
                if (tick == 3) {
                    eventQueue.add(device3.breakDevice());
                }
                inhabitant.progressTask();
                inhabitant2.progressTask();

                // Process events
//                while (!eventQueue.isEmpty()) {
//                    Event event = eventQueue.poll();
//
//                }
            }

            Visitor report = new ConsumptionReport();
            house.acceptVisitor(report);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static House buildHouse(HouseConfig houseConfig) {
        HouseBuilder houseBuilder = new HouseBuilder(houseConfig.getName());

        for (FloorConfig floorConfig : houseConfig.getFloors()) {
            FloorBuilder floorBuilder = new FloorBuilder(floorConfig.getName());

            for (RoomConfig roomConfig : floorConfig.getRooms()) {

                RoomBuilder roomBuilder = new RoomBuilder(roomConfig.getName());

                for (DeviceConfig deviceConfig : roomConfig.getDevices()) {
                    roomBuilder.addDevice(deviceConfig.getType(), deviceConfig.getName(), deviceConfig.getElectricityUsage());
                }

                for (SensorConfig sensorConfig : roomConfig.getSensors()) {
                    roomBuilder.addSensor(sensorConfig.getType(), sensorConfig.getName());
                }

                floorBuilder.addRoom(roomBuilder.build());
            }

            houseBuilder.addFloor(floorBuilder.build());
        }

        for (InhabitantConfig inhabitantConfig : houseConfig.getInhabitants()) {
            Inhabitant inhabitant = InhabitantFactory.createInhabitant(inhabitantConfig.getType(), inhabitantConfig.getName(), inhabitantConfig.getAge(), inhabitantConfig.getSpecies());
            houseBuilder.addInhabitant(inhabitant);
        }

        for (EquipmentConfig equipmentConfig : houseConfig.getEquipment()) {
            Equipment equipment = EquipmentFactory.createEquipment(equipmentConfig.getType(), equipmentConfig.getName());
            houseBuilder.addEquipment(equipment);
        }

        return houseBuilder.build();
    }


}
