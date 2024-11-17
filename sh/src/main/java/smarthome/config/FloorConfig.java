package smarthome.config;

import java.util.List;

public class FloorConfig {
    private String name;
    private List<RoomConfig> rooms;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<RoomConfig> getRooms() { return rooms; }
    public void setRooms(List<RoomConfig> rooms) { this.rooms = rooms; }
}
