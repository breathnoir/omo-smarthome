package smarthome.config;

import java.util.List;

/**
 * The {@code RoomConfig} class represents the configuration for a room in a smart home system.
 * It includes the room's name, a list of device configurations associated with the room,
 * and a list of sensor configurations to capture environmental data or interactions within the room.
 */
public class RoomConfig {
    private String name;
    private List<DeviceConfig> devices;
    private List<SensorConfig> sensors;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<DeviceConfig> getDevices() { return devices; }
    public void setDevices(List<DeviceConfig> devices) { this.devices = devices; }

    public List<SensorConfig> getSensors() { return sensors; }
    public void setSensors(List<SensorConfig> sensors) { this.sensors = sensors; }
}
