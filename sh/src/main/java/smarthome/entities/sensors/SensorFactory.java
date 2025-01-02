package smarthome.entities.sensors;

public class SensorFactory {
    public  static Sensor createSensor(String type, String name) {
        switch (type) {
            case "Temperature":
                return new TemperatureSensor(name);
            case "Humidity":
                return new HumiditySensor(name);
            case "Light":
                return new LightSensor(name);
            case "Motion":
                return new MotionSensor(name);
            case "Wind":
                return new WindSensor(name);
            default:
                return null;
        }
    }
}
