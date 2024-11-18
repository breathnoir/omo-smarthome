package smarthome.entities.devices;

public class Device {
    private String name;
    private double electricityUsage;

    public Device(String name, double electricityUsage) {
        this.name = name;
        this.electricityUsage = electricityUsage;

    }

    public String getName() {
        return name;
    }

    public double getElectricityUsage() {
        return electricityUsage;
    }
}
