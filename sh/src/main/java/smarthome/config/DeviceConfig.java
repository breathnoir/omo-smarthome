package smarthome.config;

/**
 * Represents the configuration for a device within a smart home system.
 * This class includes details about the device such as its name, type,
 * and electricity usage.
 */
public class DeviceConfig {
    private String name;
    private String type;
    private double electricityUsage;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getElectricityUsage() {
        return electricityUsage;
    }

    public void setElectricityUsage(double electricityUsage) {
        this.electricityUsage = electricityUsage;
    }
}
