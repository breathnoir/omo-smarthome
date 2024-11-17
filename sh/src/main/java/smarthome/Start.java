package smarthome;

import com.fasterxml.jackson.databind.ObjectMapper;
import smarthome.config.*;
import smarthome.entities.House;
import smarthome.entities.builders.FloorBuilder;
import smarthome.entities.builders.HouseBuilder;
import smarthome.entities.builders.RoomBuilder;
import smarthome.entities.equipment.Equipment;
import smarthome.entities.equipment.EquipmentFactory;
import smarthome.entities.inhabitants.Inhabitant;
import smarthome.entities.inhabitants.InhabitantFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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

            System.out.println("done");


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
