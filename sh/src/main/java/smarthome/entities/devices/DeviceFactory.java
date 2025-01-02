package smarthome.entities.devices;

import smarthome.entities.Room;

public class DeviceFactory {
    public  static Device createDevice(Room room, String type, String name, double electricityUsage) {
        switch (type) {
            case "Heater":
                return new Heater(room, name, electricityUsage);
            case "TV":
                return new TV(room, name, electricityUsage);
            case "AirConditioner":
                return new AirConditioner(room, name, electricityUsage);
            case "Blinds":
                return new Blinds(room, name, electricityUsage);
            case "Humidifier":
                return new Humidifier(room, name, electricityUsage);
            case "Lamp":
                return new Lamp(room, name, electricityUsage);
            case "MicrowaveOven":
                return new MicrowaveOven(room, name, electricityUsage);
            case "PC":
                return new PC(room, name, electricityUsage);
            default:
                return null;
        }
    }
}