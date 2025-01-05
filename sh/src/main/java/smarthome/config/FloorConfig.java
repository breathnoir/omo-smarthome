package smarthome.config;

import java.util.List;

/**
 * The {@code FloorConfig} class represents the configuration of a floor in a smart home system.
 * It includes settings such as the name of the floor and a list of associated rooms.
 */
public class FloorConfig {
    private String name;
    private List<RoomConfig> rooms;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<RoomConfig> getRooms() { return rooms; }
    public void setRooms(List<RoomConfig> rooms) { this.rooms = rooms; }
}
