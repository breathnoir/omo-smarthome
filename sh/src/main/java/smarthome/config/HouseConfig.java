package smarthome.config;

import java.util.List;

public class HouseConfig {
    private String name;

    private List<FloorConfig> floors;
    private List<InhabitantConfig> inhabitants;
    private List<EquipmentConfig> equipment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FloorConfig> getFloors() { return floors; }
    public void setFloors(List<FloorConfig> floors) { this.floors = floors; }

    public List<InhabitantConfig> getInhabitants() { return inhabitants; }
    public void setInhabitants(List<InhabitantConfig> inhabitants) { this.inhabitants = inhabitants; }

    public List<EquipmentConfig> getEquipment() { return equipment; }
    public void setEquipment(List<EquipmentConfig> equipment) { this.equipment = equipment; }
}
