package smarthome.entities.builders;

import smarthome.entities.equipment.Equipment;
import smarthome.entities.Floor;
import smarthome.entities.House;
import smarthome.entities.inhabitants.Inhabitant;

public class HouseBuilder {
    private House house;

    public HouseBuilder(String houseName) {
        house = new House(houseName);
    }

    public HouseBuilder addFloor(Floor floor) {
        house.addFloor(floor);
        return this;
    }

    public HouseBuilder addInhabitant(Inhabitant inhabitant) {
        house.addInhabitant(inhabitant);
        return this;
    }

    public HouseBuilder addEquipment(Equipment equipment) {
        house.addEquipment(equipment);
        return this;
    }

    public House build() {
        return house;
    }
}
