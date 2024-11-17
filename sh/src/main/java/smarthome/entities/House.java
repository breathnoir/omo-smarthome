package smarthome.entities;

import smarthome.entities.equipment.Equipment;
import smarthome.entities.inhabitants.Inhabitant;

import java.util.ArrayList;
import java.util.List;

public class House {
    private String name;
    private List<Floor> floors = new ArrayList<>();
    private List<Inhabitant> inhabitants = new ArrayList<>();
    private List<Equipment> equipment = new ArrayList<>();


    public House(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void addInhabitant(Inhabitant inhabitant) {
        inhabitants.add(inhabitant);
    }

    public void addEquipment(Equipment equipmentPiece) {
        equipment.add(equipmentPiece);
    }

}
