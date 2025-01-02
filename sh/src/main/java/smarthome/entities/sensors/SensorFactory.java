package smarthome.entities.sensors;

import smarthome.entities.Room;

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
