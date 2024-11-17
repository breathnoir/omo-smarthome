package smarthome.entities.devices;

public class DeviceFactory {
    public  static Device createDevice(String type, String name, double electricityUsage) {
        switch (type) {
            case "Heater":
                return new Heater(name, electricityUsage);
            default:
                return null;
        }
    }
}
