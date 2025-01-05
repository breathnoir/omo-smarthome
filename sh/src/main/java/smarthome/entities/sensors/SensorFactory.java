package smarthome.entities.sensors;

import smarthome.entities.Room;

/**
 * Factory class for creating different types of sensors based on the specified type.
 * Provides a centralized method that constructs sensors corresponding to various environmental
 * or activity-related monitoring functionalities for a specified room.
 */
public class SensorFactory {
    public  static Sensor createSensor(String type, Room room, String name) {
        switch (type) {
            case "Temperature":
                return new TemperatureSensor(room, name);
            case "Humidity":
                return new HumiditySensor(room, name);
            case "Light":
                return new LightSensor(room, name);
            case "Motion":
                return new MotionSensor(room, name);
            case "Wind":
                return new WindSensor(room, name);
            default:
                return null;
        }
    }
}
