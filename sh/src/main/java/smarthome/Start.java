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
    /**
     * The main method initializes the simulation of a smart home system by setting up
     * a house configuration and running the simulation. It reads the configuration file,
     * constructs a house with its components, and starts the simulation.
     *
     * @param args command-line arguments passed to the program. Not used in this method.
     */
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        String configFile = "houseconfig.json"; // smaller house
//        String configFile = "houseconfig2.json"; // bigger house

        try {
            InputStream inputStream = Start.class.getClassLoader().getResourceAsStream(configFile);

            if (inputStream == null) {
                throw new FileNotFoundException("Resource not found: " + configFile);
            }

            HouseConfig houseConfig = mapper.readValue(inputStream, HouseConfig.class);
            House house = buildHouse(houseConfig);

            Simulation simulation = Simulation.getInstance();
            simulation.setHouse(house);
            simulation.run();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds a complete {@link House} object using the provided configuration.
     * This method assembles the house by constructing its components such as floors,
     * rooms, devices, sensors, inhabitants, and equipment using the specified
     * configuration details.
     *
     * @param houseConfig the configuration object that defines the structure and attributes
     *                    of the house, including its floors, rooms, devices, sensors,
     *                    inhabitants, and equipment.
     * @return a fully constructed {@link House} object based on the provided configuration.
     */
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
