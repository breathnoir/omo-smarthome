package smarthome.entities.sensors;

public class SensorFactory {
    public  static Sensor createSensor(String type, String name) {
        switch (type) {
            case "Temperature":
                return new TemperatureSensor(name);
            default:
                return null;
        }
    }
}
