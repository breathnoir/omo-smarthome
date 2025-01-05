package smarthome.config;

/**
 * Represents the configuration for a piece of equipment within a smart home system.
 * This class stores information about the equipment's name and type.
 */
public class EquipmentConfig {
    private String name;
    private String type;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
