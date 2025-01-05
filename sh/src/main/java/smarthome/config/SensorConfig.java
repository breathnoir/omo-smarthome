package smarthome.config;

/**
 * Represents the configuration for a sensor within a smart home system.
 * This class includes information about the sensor's name and type, which
 * describes its functionality and purpose.
 */
public class SensorConfig {
    private String name;
    private String type;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
